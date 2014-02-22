package com.wso2.build.beans;

import com.wso2.build.enums.RuleCategory;
import org.xml.sax.InputSource;

import java.io.Serializable;

/**
 * Created by uvindra on 2/9/14.
 */
public class Rule implements Serializable {
    private String name;
    private RuleCategory category;
    private boolean isActive;
    private String compatibleMavenVersion;
    private InputSource pluginUsage;

    public Rule() {
        this.name = "";
        this.category = RuleCategory.DEFAULT;
        this.isActive = false;
        this.compatibleMavenVersion = "";
        this.pluginUsage = null;
    }

    public Rule(String name, RuleCategory category, boolean isActive,
                String compatibleMavenVersion, InputSource pluginUsage) {
        this.name = name;
        this.category = category;
        this.isActive = isActive;
        this.compatibleMavenVersion = compatibleMavenVersion;
        this.pluginUsage = pluginUsage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RuleCategory getCategory() {
        return category;
    }

    public void setCategory(RuleCategory category) {
        this.category = category;
    }

    public String getCompatibleMavenVersion() {
        return compatibleMavenVersion;
    }

    public void setCompatibleMavenVersion(String compatibleMavenVersion) {
        this.compatibleMavenVersion = compatibleMavenVersion;
    }

    public InputSource getPluginUsage() {
        return pluginUsage;
    }

    public void setPluginUsage(InputSource pluginUsage) {
        this.pluginUsage = pluginUsage;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
