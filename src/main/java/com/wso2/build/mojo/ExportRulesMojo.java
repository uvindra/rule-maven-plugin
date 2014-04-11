package com.wso2.build.mojo;

import com.wso2.build.beans.Parameters;
import com.wso2.build.core.Configs;
import com.wso2.build.registry.GRegRuleRegistry;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.settings.Settings;


/**
 * Created by uvindra on 4/11/14.
 * @goal export
 */
public class ExportRulesMojo extends AbstractMojo {
    /**
     * The settings.xml file in .m2.
     *
     * @parameter expression="${settings}"
     * @required
     */
    private Settings settings;

    @Override
    public void execute() throws MojoExecutionException {
        Parameters parameters = Configs.loadParameters(settings);

        GRegRuleRegistry regRuleRegistry = new GRegRuleRegistry(parameters);

        regRuleRegistry.exportRules(getLog());
    }
}