package com.wso2.build.factories;

import com.wso2.build.beans.GRegParameters;
import com.wso2.build.interfaces.Factory;
import com.wso2.build.interfaces.PluginConfigParser;
import com.wso2.build.interfaces.RuleRegistry;
import com.wso2.build.parser.SAXConfigParser;
import com.wso2.build.registry.GRegRuleRegistry;
import org.codehaus.plexus.component.annotations.Component;

import java.util.Properties;

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
    public RuleRegistry getRegistry(Properties properties) {
        GRegParameters parameters = new GRegParameters();

        parameters.setGregHome(properties.getProperty("greg.home"));
        parameters.setGregEndpoint(properties.getProperty("greg.endpoint"));
        parameters.setGregUsername(properties.getProperty("greg.username"));
        parameters.setGregPassword(properties.getProperty("greg.password"));
        parameters.setTrustStorePassword(properties.getProperty("trust.store.password"));

        return new GRegRuleRegistry(parameters);
    }
}
