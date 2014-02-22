package com.wso2.build.interfaces;

import com.wso2.build.beans.Parameters;


/**
 * Created by uvindra on 2/9/14.
 */
public interface Factory {
    PluginConfigParser getParser();
    RuleRegistry getRegistry(Parameters parameters);
}
