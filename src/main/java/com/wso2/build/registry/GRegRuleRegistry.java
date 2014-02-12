package com.wso2.build.registry;

import com.wso2.build.beans.GRegParameters;
import com.wso2.build.beans.Rule;
import com.wso2.build.interfaces.RuleRegistry;
import com.wso2.build.stub.GetValidationServiceGovernanceException;
import com.wso2.build.stub.GetValidationArtifactIDsServiceGovernanceException;
import com.wso2.build.stub.ValidationStub;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.wso2.carbon.base.ServerConfiguration;
import org.wso2.carbon.utils.CarbonUtils;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by uvindra on 2/8/14.
 */
public class GRegRuleRegistry implements RuleRegistry{

    private ConfigurationContext configContext = null;
    private ValidationStub stub = null;
    private String CARBON_HOME;
    private String axis2Repo;
    private String username;
    private String password;
    private String serverURL;

    private static final String axis2Conf = ServerConfiguration.getInstance().getFirstProperty("Axis2Config.clientAxis2XmlLocation");
    private static final String pluginUsageStartTag = "<pluginUsage>";
    private static final String pluginUsageEndTag = "</pluginUsage>";
    private static final String nameStartTag = "<name>";
    private static final String nameEndTag = "</name>";
    private static final String categoryStartTag = "<category>";
    private static final String categoryEndTag = "</category>";
    private static final String statusStartTag = "<status>";
    private static final String statusEndTag = "</status>";
    private static final String compatibleMavenVersionStartTag = "<compatibleMavenVersion>";
    private static final String compatibleMavenVersionEndTag = "</compatibleMavenVersion>";
    private static final String ruleActiveStatus = "Active";

    public GRegRuleRegistry(GRegParameters parameters) {
        CARBON_HOME = parameters.getGregHome();
        axis2Repo = CARBON_HOME + File.separator +"repository" + File.separator +
                                    "deployment" + File.separator + "client";

        username = parameters.getGregUsername();
        password = parameters.getGregPassword();
        serverURL = parameters.getGregEndpoint();

        System.setProperty("javax.net.ssl.trustStore", CARBON_HOME + File.separator + "repository" +
                File.separator + "resources" + File.separator + "security" + File.separator +
                "wso2carbon.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", parameters.getTrustStorePassword());
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("carbon.repo.write.mode", "true");
    }
    
    @Override
    public List<Rule> getRules() {
        try {
            configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem(
                    axis2Repo, axis2Conf);

            stub = new ValidationStub(configContext, serverURL, false);

            CarbonUtils.setBasicAccessSecurityHeaders(username, password, stub._getServiceClient());
        }
        catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }

        String[] artifactIds = getValidationArtifactIDs();

        List<Rule> ruleList = new LinkedList<Rule>();

        for (int i = 0; i< artifactIds.length; ++i) {
            String rule = getValidation(artifactIds[i]);

            int tagStart = rule.indexOf(nameStartTag);
            int tagEnd = rule.indexOf(nameEndTag);

            String name = rule.substring(tagStart + nameStartTag.length(), tagEnd);

            tagStart = rule.indexOf(categoryStartTag);
            tagEnd = rule.indexOf(categoryEndTag);

            String category = rule.substring(tagStart + categoryStartTag.length(), tagEnd);

            tagStart = rule.indexOf(statusStartTag);
            tagEnd = rule.indexOf(statusEndTag);

            String status = rule.substring(tagStart + statusStartTag.length(), tagEnd);

            boolean isActive = false;

            if (status.equalsIgnoreCase(ruleActiveStatus)) {
                isActive = true;
            }

            tagStart = rule.indexOf(compatibleMavenVersionStartTag);
            tagEnd = rule.indexOf(compatibleMavenVersionEndTag);

            String mvnVersion = rule.substring(tagStart + compatibleMavenVersionStartTag.length(), tagEnd);

            tagStart = rule.indexOf(pluginUsageStartTag);
            tagEnd = rule.indexOf(pluginUsageEndTag);

            String pluginBody = rule.substring(tagStart + pluginUsageStartTag.length(), tagEnd);

            pluginBody = pluginBody.replace("&lt;", "<");
            pluginBody = pluginBody.replace("&gt;", ">");

            ruleList.add(new Rule(name, category, isActive, mvnVersion, new InputSource(new StringReader(pluginBody))));
        }

        return ruleList;
    }

    private String[] getValidationArtifactIDs() {
        ValidationStub.GetValidationArtifactIDs artifactIDs = new ValidationStub.GetValidationArtifactIDs();
        ValidationStub.GetValidationArtifactIDsResponse getModuleArtifactIDsResponse = null;

        String[] artifactIds = new String[0];

        try {
            getModuleArtifactIDsResponse = stub.getValidationArtifactIDs(artifactIDs);

            artifactIds = getModuleArtifactIDsResponse.get_return();
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
        catch (GetValidationArtifactIDsServiceGovernanceException e) {
            e.printStackTrace();
        }

        return artifactIds;
    }

    private String getValidation(String artifactID){
        ValidationStub.GetValidation getValidation = new ValidationStub.GetValidation();
        getValidation.setArtifactId(artifactID);

        String validation = "";

        try {
            ValidationStub.GetValidationResponse getValidationResponse = stub.getValidation(getValidation);

            validation = getValidationResponse.get_return();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (GetValidationServiceGovernanceException e) {
            e.printStackTrace();
        }

        return validation;
    }
}
