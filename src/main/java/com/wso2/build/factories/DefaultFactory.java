package com.wso2.build.factories;

import com.wso2.build.beans.Parameters;
import com.wso2.build.interfaces.Factory;
import com.wso2.build.interfaces.PluginConfigParser;
import com.wso2.build.interfaces.RuleRegistry;
import com.wso2.build.parser.SAXConfigParser;
import com.wso2.build.registry.GRegRuleRegistry;
import org.codehaus.plexus.component.annotations.Component;


/**
 * Created by uvindra on 2/9/14.
 */
@Component( role = Factory.class, hint = "default" )
public class DefaultFactory implements Factory {

    @Override
    public PluginConfigParser getParser() {
        return new SAXConfigParser();
    }

    @Override
    public RuleRegistry getRegistry(Parameters parameters) {
        return new GRegRuleRegistry(parameters);
    }
}
