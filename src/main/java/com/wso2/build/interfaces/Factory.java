package com.wso2.build.interfaces;

/**
 * Created by uvindra on 2/9/14.
 */
public interface Factory {
    PluginConfigParser getParser();
    RuleRegistry getRegistry();
}
