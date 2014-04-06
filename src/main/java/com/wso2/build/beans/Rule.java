package com.wso2.build.beans;

import com.wso2.build.enums.RuleCategory;
import com.wso2.build.enums.RuleType;
import java.io.Serializable;

/**
 * Created by uvindra on 2/9/14.
 */
public class Rule implements Serializable {
    private String name = "";
    private RuleCategory category = RuleCategory.DEFAULT;
    private RuleType type = RuleType.PLUGIN;
    private boolean isActive = false;
    private String compatibleMavenVersion = "";
    private String definition;

    public Rule(String name, RuleCategory category, RuleType type, boolean isActive,
                String compatibleMavenVersion, String definition) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.isActive = isActive;
        this.compatibleMavenVersion = compatibleMavenVersion;
        this.definition = definition;
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

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public RuleType getType() {
        return type;
    }

    public void setType(RuleType type) {
        this.type = type;
    }
}
