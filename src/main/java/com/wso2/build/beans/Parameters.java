package com.wso2.build.beans;

import java.io.Serializable;

/**
 * Created by uvindra on 2/12/14.
 */
public class Parameters implements Serializable {
    private String homePath;
    private String ruleEndpoint;
    private String lifeCycleEndpoint;
    private String moduleEndpoint;
    private String dependencyEndpoint;
    private String artifactEndpoint;
    private String packageEndpoint;
    private String registryUsername;
    private String registryPassword;
    private String trustStorePassword;
    private String excludes;
    private String explicits;

    public String getHomePath() {
        return homePath;
    }

    public void setHomePath(String homePath) {
        this.homePath = homePath;
    }

    public String getRuleEndpoint() {
        return ruleEndpoint;
    }

    public void setRuleEndpoint(String ruleEndpoint) {
        this.ruleEndpoint = ruleEndpoint;
    }

    public String getRegistryUsername() {
        return registryUsername;
    }

    public void setRegistryUsername(String registryUsername) {
        this.registryUsername = registryUsername;
    }

    public String getRegistryPassword() {
        return registryPassword;
    }

    public void setRegistryPassword(String registryPassword) {
        this.registryPassword = registryPassword;
    }

    public String getTrustStorePassword() {
        return trustStorePassword;
    }

    public void setTrustStorePassword(String trustStorePassword) {
        this.trustStorePassword = trustStorePassword;
    }

    public String getLifeCycleEndpoint() {
        return lifeCycleEndpoint;
    }

    public void setLifeCycleEndpoint(String lifeCycleEndpoint) {
        this.lifeCycleEndpoint = lifeCycleEndpoint;
    }

    public String getExcludes() {
        return excludes;
    }

    public void setExcludes(String excludes) {
        this.excludes = excludes;
    }

    public String getExplicits() {
        return explicits;
    }

    public void setExplicits(String explicits) {
        this.explicits = explicits;
    }

    public String getModuleEndpoint() {
        return moduleEndpoint;
    }

    public void setModuleEndpoint(String moduleEndpoint) {
        this.moduleEndpoint = moduleEndpoint;
    }

    public String getDependencyEndpoint() {
        return dependencyEndpoint;
    }

    public void setDependencyEndpoint(String dependencyEndpoint) {
        this.dependencyEndpoint = dependencyEndpoint;
    }

    public String getArtifactEndpoint() {
        return artifactEndpoint;
    }

    public void setArtifactEndpoint(String artifactEndpoint) {
        this.artifactEndpoint = artifactEndpoint;
    }

    public String getPackageEndpoint() {
        return packageEndpoint;
    }

    public void setPackageEndpoint(String packageEndpoint) {
        this.packageEndpoint = packageEndpoint;
    }
}
