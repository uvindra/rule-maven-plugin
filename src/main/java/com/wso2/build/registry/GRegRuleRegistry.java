package com.wso2.build.registry;


import com.wso2.build.beans.Parameters;
import com.wso2.build.beans.Rule;
import com.wso2.build.enums.RuleCategory;
import com.wso2.build.interfaces.RuleRegistry;
import com.wso2.build.stub.*;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.maven.plugin.MojoExecutionException;
import org.wso2.carbon.base.ServerConfiguration;
import org.wso2.carbon.utils.CarbonUtils;
import org.xml.sax.InputSource;
import java.io.*;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by uvindra on 2/8/14.
 */
public class GRegRuleRegistry implements RuleRegistry{

    private ConfigurationContext configContext = null;
    private BuildRuleStub ruleStub = null;
    private CustomLifecyclesChecklistAdminServiceStub lifecycleStub = null;
    private String GREG_HOME;
    private String axis2Repo;
    Parameters parameters = null;

    private static final String axis2Conf = ServerConfiguration.getInstance().getFirstProperty("Axis2Config.clientAxis2XmlLocation");
    private static final String pluginUsageStartTag = "<pluginUsage>";
    private static final String pluginUsageEndTag = "</pluginUsage>";
    private static final String nameStartTag = "<name>";
    private static final String nameEndTag = "</name>";
    private static final String categoryStartTag = "<category>";
    private static final String categoryEndTag = "</category>";
    private static final String compatibleMavenVersionStartTag = "<version>";
    private static final String compatibleMavenVersionEndTag = "</version>";
    private static final String ruleActiveStatus = "Active";


    public GRegRuleRegistry(Parameters parameters) {
        GREG_HOME = parameters.getGregHome();
        axis2Repo = GREG_HOME + File.separator +"repository" + File.separator +
                                    "deployment" + File.separator + "client";

        this.parameters = parameters;

        System.setProperty("javax.net.ssl.trustStore", GREG_HOME + File.separator + "repository" +
                File.separator + "resources" + File.separator + "security" + File.separator +
                "wso2carbon.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", parameters.getTrustStorePassword());
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("carbon.repo.write.mode", "true");
    }
    
    @Override
    public List<Rule> getRules() throws MojoExecutionException {
        try {
            configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem(
                    axis2Repo, axis2Conf);

            ruleStub = new BuildRuleStub(configContext, parameters.getGregRuleEndpoint(), false);
            lifecycleStub = new CustomLifecyclesChecklistAdminServiceStub(configContext, parameters.getGregLifeCycleEndpoint(), false);

            CarbonUtils.setBasicAccessSecurityHeaders(parameters.getGregUsername(), parameters.getGregPassword(), ruleStub._getServiceClient());
            CarbonUtils.setBasicAccessSecurityHeaders(parameters.getGregUsername(), parameters.getGregPassword(), lifecycleStub._getServiceClient());
        }
        catch (AxisFault axisFault) {
            axisFault.printStackTrace();
            throw new MojoExecutionException("AxisFault thrown : " + axisFault.getMessage());
        }

        String[] artifactIds = getRuleArtifactIds();

        List<Rule> ruleList = new LinkedList<Rule>();

        for (String artifactId : artifactIds) {
            String rule = getRule(artifactId);

            if (rule.isEmpty()) {
                continue;
            }

            int tagStart = rule.indexOf(nameStartTag);
            int tagEnd = rule.indexOf(nameEndTag);

            String name = rule.substring(tagStart + nameStartTag.length(), tagEnd);

            tagStart = rule.indexOf(categoryStartTag);
            tagEnd = rule.indexOf(categoryEndTag);

            String category = rule.substring(tagStart + categoryStartTag.length(), tagEnd);

            tagStart = rule.indexOf(compatibleMavenVersionStartTag);
            tagEnd = rule.indexOf(compatibleMavenVersionEndTag);

            String mvnVersion = rule.substring(tagStart + compatibleMavenVersionStartTag.length(), tagEnd);

            String status = getLifecycleState(name, mvnVersion);

            boolean isActive = false;

            if (status.equalsIgnoreCase(ruleActiveStatus)) {
                isActive = true;
            }

            tagStart = rule.indexOf(pluginUsageStartTag);
            tagEnd = rule.indexOf(pluginUsageEndTag);

            String pluginBody = rule.substring(tagStart + pluginUsageStartTag.length(), tagEnd);

            pluginBody = pluginBody.replace("&lt;", "<");
            pluginBody = pluginBody.replace("&gt;", ">");

            ruleList.add(new Rule(name, RuleCategory.getValue(category), isActive, mvnVersion, new InputSource(new StringReader(pluginBody))));
        }

        return ruleList;
    }


    private String[] getRuleArtifactIds() throws MojoExecutionException {
        String[] artifactIds = new String[0];

        try {
            BuildRuleStub.GetBuildRuleArtifactIDs artifactIDs = new BuildRuleStub.GetBuildRuleArtifactIDs();
            BuildRuleStub.GetBuildRuleArtifactIDsResponse getResponse = ruleStub.getBuildRuleArtifactIDs(artifactIDs);

            artifactIds = getResponse.get_return();
        }
        catch (RemoteException e) {
            e.printStackTrace();
            throw new MojoExecutionException("RemoteException thrown : " + e.getMessage());
        }
       catch (GetBuildRuleArtifactIDsServiceGovernanceException e) {
            e.printStackTrace();
           throw new MojoExecutionException("GetBuildRuleArtifactIDsServiceGovernanceException thrown : " + e.getMessage());
        }

        return artifactIds;
    }


    private String getRule(String artifactID) throws MojoExecutionException {
        BuildRuleStub.GetBuildRule getBuildRule = new BuildRuleStub.GetBuildRule();
        getBuildRule.setArtifactId(artifactID);

        String rule = "";

        try {
            BuildRuleStub.GetBuildRuleResponse getResponse = ruleStub.getBuildRule(getBuildRule);

            rule = getResponse.get_return();

        } catch (RemoteException e) {
            e.printStackTrace();
            throw new MojoExecutionException("RemoteException thrown : " + e.getMessage());
        } catch (GetBuildRuleServiceGovernanceException e) {
            e.printStackTrace();
            throw new MojoExecutionException("GetBuildRuleServiceGovernanceException thrown : " + e.getMessage());
        }

        return rule;
    }

    private String getLifecycleState(String name, String version) throws MojoExecutionException {
        CustomLifecyclesChecklistAdminServiceStub.GetLifecycleBean getLifecycleBean = new CustomLifecyclesChecklistAdminServiceStub.GetLifecycleBean();

        String fullPath = "/_system/governance/trunk/buidlrules/" + name + "/" + version;

        getLifecycleBean.setPath(fullPath);

        CustomLifecyclesChecklistAdminServiceStub.LifecycleBean lifecycleBean = null;

        try {
            CustomLifecyclesChecklistAdminServiceStub.GetLifecycleBeanResponse getLifecycleBeanResponse = lifecycleStub.getLifecycleBean(getLifecycleBean);

            lifecycleBean = getLifecycleBeanResponse.get_return();

        } catch (RemoteException e) {
            e.printStackTrace();
            throw new MojoExecutionException("RemoteException thrown : " + e.getMessage());
        } catch (CustomLifecyclesChecklistAdminServiceExceptionException e) {
            e.printStackTrace();
            throw new MojoExecutionException("CustomLifecyclesChecklistAdminServiceExceptionException thrown : " + e.getMessage());
        }

        CustomLifecyclesChecklistAdminServiceStub.Property[] properties = lifecycleBean.getLifecycleProperties();

        String lifeCycleState;

        if (0 == properties.length) {  // Assumption that there will be at least one property
            throw new IllegalStateException("LifeCycle properties are not available");
        }
        else { // The first property will be store the state of the Life Cycle
            String[] values = properties[0].getValues();

            if (1 != values.length) { //Assumption that there will be only a single value for the property
                throw new IllegalStateException("LifeCycle property contains multiple values");
            }
            else {
                lifeCycleState = values[0];
            }
        }

        return lifeCycleState;
    }
}
