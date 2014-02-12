package com.wso2.build.interfaces;

import java.util.Properties;

/**
 * Created by uvindra on 2/9/14.
 */
public interface Factory {
    PluginConfigParser getParser();
    RuleRegistry getRegistry(Properties properties);
}
