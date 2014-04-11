package com.wso2.build.registry;

import com.wso2.build.beans.Parameters;
import com.wso2.build.stub.*;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.wso2.carbon.base.ServerConfiguration;
import org.wso2.carbon.utils.CarbonUtils;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by uvindra on 4/8/14.
 */
public class PackageBundleClient {

    private ConfigurationContext configContext = null;
    private OSGiServiceStub serviceStub = null;

    private static HashMap<String, TreeSet<String>> packageVersions = new HashMap<String, TreeSet<String>>();

    private static final String nameStartTag = "<name>";
    private static final String nameEndTag = "</name>";
    private static final String versionStartTag = "<version>";
    private static final String versionEndTag = "</version>";

    private static final String axis2Conf = ServerConfiguration.getInstance().getFirstProperty("Axis2Config.clientAxis2XmlLocation");

    public void loadPackageBundles(Parameters parameters) {
        setupPackageInfo(parameters);
    }

    public boolean isLatestExportedVersion(final String packageName, final String version) {
        TreeSet<String> versionSet = packageVersions.get(packageName);

        if (null == versionSet) { // Package does not exist
            return true;
        }

        String latestVersion = versionSet.last();

        if (-1 == version.compareTo(latestVersion)) { // Specified version is older than latest version
            return false;
        }

        return true;
    }

    public boolean isVersionExported(final String packageName, final String version) {
        TreeSet<String> versionSet = packageVersions.get(packageName);

        if (null == versionSet) { // Package does not exist
            return true;
        }

        return versionSet.contains(version);
    }


    public String getLatestVersion(final String packageName) {
        String version = "";

        TreeSet<String> versionSet = packageVersions.get(packageName);

        if (null != versionSet) { // Package does not exist
            version = versionSet.last();
        }

        return version;
    }


    public List<String> getExportedVersions(final String packageName) {
        List<String> versions = new ArrayList<String>();

        TreeSet<String> versionSet = packageVersions.get(packageName);

        if (null != versionSet) { // Package does not exist
            versions.addAll(versionSet);
        }

        return versions;
    }



    private void setupPackageInfo(Parameters parameters) {
        if (!packageVersions.isEmpty()) {
            return;
        }

        try {
            String axis2Repo = parameters.getHomePath() + File.separator +"repository" + File.separator +
                    "deployment" + File.separator + "client";

            System.setProperty("javax.net.ssl.trustStore", parameters.getHomePath() + File.separator + "repository" +
                    File.separator + "resources" + File.separator + "security" + File.separator +
                    "wso2carbon.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", parameters.getTrustStorePassword());
            System.setProperty("javax.net.ssl.trustStoreType", "JKS");
            System.setProperty("carbon.repo.write.mode", "true");

            configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem(
                    axis2Repo, axis2Conf);

            serviceStub = new OSGiServiceStub(configContext, parameters.getPackageEndpoint(), false);

            CarbonUtils.setBasicAccessSecurityHeaders(parameters.getRegistryUsername(), parameters.getRegistryPassword(), serviceStub._getServiceClient());
        }
        catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }

        String[] artifactIds = getArtifactIDs();

        for (String artifactId : artifactIds) {
            String service = getService(artifactId);

            int tagStart = service.indexOf(nameStartTag);
            int tagEnd = service.indexOf(nameEndTag);

            String name = service.substring(tagStart + nameStartTag.length(), tagEnd);

            tagStart = service.indexOf(versionStartTag);
            tagEnd = service.indexOf(versionEndTag);

            String version = service.substring(tagStart + versionStartTag.length(), tagEnd);

            if (!packageVersions.containsKey(name)) {
                TreeSet<String> versionSet = new TreeSet<String>();
                versionSet.add(version);

                packageVersions.put(name, versionSet);
            }
            else {
                TreeSet<String> versionSet = packageVersions.get(name);
                versionSet.add(version);
            }
        }
    }


    private String[] getArtifactIDs() {
        OSGiServiceStub.GetOSGiServiceArtifactIDs artifactIDs = new OSGiServiceStub.GetOSGiServiceArtifactIDs();
        OSGiServiceStub.GetOSGiServiceArtifactIDsResponse getOSGiServiceArtifactIDsResponse = null;

        String[] artifactIdStrings = new String[0];

        try {
            getOSGiServiceArtifactIDsResponse = serviceStub.getOSGiServiceArtifactIDs(artifactIDs);

            artifactIdStrings = getOSGiServiceArtifactIDsResponse.get_return();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (GetOSGiServiceArtifactIDsServiceGovernanceException e) {
            e.printStackTrace();
        }

        return artifactIdStrings;
    }

    private String getService(String artifactID){
        OSGiServiceStub.GetOSGiService getService = new OSGiServiceStub.GetOSGiService();
        getService.setArtifactId(artifactID);

        String service = "";

        try {
            OSGiServiceStub.GetOSGiServiceResponse getServiceResponse = serviceStub.getOSGiService(getService);

            service = getServiceResponse.get_return();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (GetOSGiServiceServiceGovernanceException e) {
            e.printStackTrace();
        }

        return service;
    }
}
