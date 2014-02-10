package com.wso2.build.interfaces;

import org.xml.sax.InputSource;
import org.codehaus.plexus.util.xml.Xpp3Dom;

/**
 * Created by uvindra on 2/8/14.
 */
public interface PluginConfigParser {
    void parseConfigs(InputSource ruleSource);
    String getGroupId();
    String getArtifactId();
    String getVersion();
    String getGoal();
    String getId();
    Xpp3Dom getConfiguration();
    String getPhase();
}
