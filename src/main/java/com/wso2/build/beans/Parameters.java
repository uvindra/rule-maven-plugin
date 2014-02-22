package com.wso2.build.beans;

import java.io.Serializable;

/**
 * Created by uvindra on 2/12/14.
 */
public class Parameters implements Serializable {
    private String gregHome;
    private String gregRuleEndpoint;
    private String gregLifeCycleEndpoint;
    private String gregUsername;
    private String gregPassword;
    private String trustStorePassword;
    private String excludes;
    private String explicits;

    public String getGregHome() {
        return gregHome;
    }

    public void setGregHome(String gregHome) {
        this.gregHome = gregHome;
    }

    public String getGregRuleEndpoint() {
        return gregRuleEndpoint;
    }

    public void setGregRuleEndpoint(String gregRuleEndpoint) {
        this.gregRuleEndpoint = gregRuleEndpoint;
    }

    public String getGregUsername() {
        return gregUsername;
    }

    public void setGregUsername(String gregUsername) {
        this.gregUsername = gregUsername;
    }

    public String getGregPassword() {
        return gregPassword;
    }

    public void setGregPassword(String gregPassword) {
        this.gregPassword = gregPassword;
    }

    public String getTrustStorePassword() {
        return trustStorePassword;
    }

    public void setTrustStorePassword(String trustStorePassword) {
        this.trustStorePassword = trustStorePassword;
    }

    public String getGregLifeCycleEndpoint() {
        return gregLifeCycleEndpoint;
    }

    public void setGregLifeCycleEndpoint(String gregLifeCycleEndpoint) {
        this.gregLifeCycleEndpoint = gregLifeCycleEndpoint;
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
}
