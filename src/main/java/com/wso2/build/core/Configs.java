package com.wso2.build.core;

import com.wso2.build.beans.Parameters;
import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Settings;

import java.util.Map;
import java.util.Properties;

/**
 * Created by uvindra on 4/11/14.
 */
public class Configs {
    public static Parameters loadParameters(Settings settings) {
        Map<String, Profile> profileMap = settings.getProfilesAsMap();

        Profile profile = profileMap.get("rule");

        Properties properties = profile.getProperties();

        Parameters parameters = new Parameters();

        parameters.setHomePath(properties.getProperty("home"));
        parameters.setModuleEndpoint(properties.getProperty("module.endpoint"));
        parameters.setDependencyEndpoint(properties.getProperty("dependency.endpoint"));
        parameters.setArtifactEndpoint(properties.getProperty("artifact.endpoint"));
        parameters.setRuleEndpoint(properties.getProperty("rule.endpoint"));
        parameters.setLifeCycleEndpoint(properties.getProperty("lifecycle.endpoint"));
        parameters.setPackageEndpoint(properties.getProperty("package.endpoint"));
        parameters.setRegistryUsername(properties.getProperty("username"));
        parameters.setRegistryPassword(properties.getProperty("password"));
        parameters.setTrustStorePassword(properties.getProperty("trust.store.password"));
        parameters.setExcludes(properties.getProperty("exclude"));
        parameters.setExplicits(properties.getProperty("explicit"));

        return parameters;
    }
}
