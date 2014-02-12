package com.wso2.build.beans;

import java.io.Serializable;

/**
 * Created by uvindra on 2/12/14.
 */
public class GRegParameters implements Serializable {
    private String gregHome;
    private String gregEndpoint;
    private String gregUsername;
    private String gregPassword;
    private String trustStorePassword;

    public String getGregHome() {
        return gregHome;
    }

    public void setGregHome(String gregHome) {
        this.gregHome = gregHome;
    }

    public String getGregEndpoint() {
        return gregEndpoint;
    }

    public void setGregEndpoint(String gregEndpoint) {
        this.gregEndpoint = gregEndpoint;
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
}
