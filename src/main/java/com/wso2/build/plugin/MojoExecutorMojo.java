package com.wso2.build.plugin;

import com.wso2.build.beans.Parameters;
import com.wso2.build.beans.Rule;
import com.wso2.build.enums.RuleCategory;
import com.wso2.build.interfaces.Factory;
import com.wso2.build.interfaces.FactoryContainer;
import com.wso2.build.interfaces.PluginConfigParser;
import com.wso2.build.interfaces.RuleRegistry;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginExecution;
import org.apache.maven.settings.Profile;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.rtinfo.RuntimeInformation;
import org.apache.maven.settings.Settings;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.PlexusContainerException;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.util.xml.Xpp3Dom;


import java.util.*;

import static org.twdata.maven.mojoexecutor.MojoExecutor.*;

/**
 * Created by uvindra on 2/9/14.
 * @goal check
 * @requiresDependencyResolution test
 */
public class MojoExecutorMojo extends AbstractMojo {
    /**
     * The project currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     * The current Maven session.
     *
     * @parameter expression="${session}"
     * @required
     * @readonly
     */
    private MavenSession session;

    /**
     * The Maven BuildPluginManager component.
     *
     * @component
     * @required
     */
    private BuildPluginManager pluginManager;

    /**
     * The Maven RuntimeInformation component.
     *
     * @component
     * @required
     */
    private RuntimeInformation runtime;

    /**
     * The settings.xml file in .m2.
     *
     * @parameter expression="${settings}"
     * @required
     */
    private Settings settings;


    private FactoryContainer factoryContainer = null;



    @Override
    public void execute() throws MojoExecutionException {
        Parameters parameters = loadParameters();

        try {
            PlexusContainer container = new DefaultPlexusContainer();

            factoryContainer = container.lookup(FactoryContainer.class);

            Factory factory = factoryContainer.getFactory("default");

            executeRules(factory, parameters);

            // stop the components and container
            container.dispose();
        }
        catch (PlexusContainerException e) {
            e.printStackTrace();

            throw new MojoExecutionException("Default Plexus container could not be instantiated");
        }
        catch (ComponentLookupException e) {
            e.printStackTrace();

            throw new MojoExecutionException("Factory container could not be instantiated");
        }
    }


    private void executeRules(Factory factory, Parameters parameters) throws MojoExecutionException {
        RuleRegistry registry = factory.getRegistry(parameters);

        List<Rule> ruleList = registry.getRules();

        for(Rule rule : ruleList) {

            if (true != rule.isActive()) {
                continue;
            }

            if (true == isRuleExcluded(rule.getName(), parameters)) {
                continue;
            }

            if (true != isRuleExecutable(rule.getName(), rule.getCategory(), parameters)) {
                continue;
            }

            if (isMavenCompatible(rule.getCompatibleMavenVersion())) {

                PluginConfigParser parser = factory.getParser();

                parser.parseConfigs(rule.getPluginUsage());

                run(parser.getGroupId(), parser.getArtifactId(), parser.getVersion(), parser.getGoal(),
                        parser.getId(), parser.getConfiguration());
            }
        }
    }

    private void run(String groupId, String artifactId, String version,
                             String goal, String id, Xpp3Dom configuration) throws MojoExecutionException{
        Plugin plugin = new Plugin();
        plugin.setGroupId(groupId);
        plugin.setArtifactId(artifactId);
        plugin.setVersion(version);

        PluginExecution pluginExecution = new PluginExecution();

        pluginExecution.setId(id);

        List<String> goals = new LinkedList<String>();
        goals.add(goal);
        pluginExecution.setGoals(goals);

        plugin.addExecution(pluginExecution);

        executeMojo(plugin, goal, configuration,
                executionEnvironment(project, session, pluginManager));
    }

    private Parameters loadParameters() {
        Map<String, Profile> profileMap = settings.getProfilesAsMap();

        Profile profile = profileMap.get("rule");

        Properties properties = profile.getProperties();

        Parameters parameters = new Parameters();

        parameters.setGregHome(properties.getProperty("greg.home"));
        parameters.setGregRuleEndpoint(properties.getProperty("greg.rule.endpoint"));
        parameters.setGregLifeCycleEndpoint(properties.getProperty("greg.lifecycle.endpoint"));
        parameters.setGregUsername(properties.getProperty("greg.username"));
        parameters.setGregPassword(properties.getProperty("greg.password"));
        parameters.setTrustStorePassword(properties.getProperty("trust.store.password"));
        parameters.setExcludes(properties.getProperty("exclude"));
        parameters.setExplicits(properties.getProperty("explicit"));

        return parameters;
    }


    private boolean isMavenCompatible(String mavenVersion) {
        String[] tokenizedVersion = mavenVersion.split("\\.");

        // Version specified as wild card, skip the validation
        if (1 == tokenizedVersion.length && tokenizedVersion[0].equalsIgnoreCase("x")) {
            return true;
        }

        String localMavenVersion = runtime.getMavenVersion();

        String[] tokenizedLocalVersion = localMavenVersion.split("\\.");

        if (tokenizedVersion.length == tokenizedLocalVersion.length) {
            for (int i = 0; i < tokenizedVersion.length; ++i) {
                if (tokenizedVersion[i].equalsIgnoreCase("x")) {  // Wild card match
                    continue;
                }
                else if (false == tokenizedVersion[i].equalsIgnoreCase(tokenizedLocalVersion[i])) {
                    return false;   // Token mismatch
                }
            }
        }
        else {  // Token lengths differ so version format is different
            return false;
        }

        return true;
    }


    private boolean isRuleExcluded(String name, Parameters parameters) {
        String excludedRules = parameters.getExcludes();

        if (null == excludedRules || excludedRules.isEmpty()) {
            return false;
        }

        return excludedRules.contains(name);
    }

    private boolean isRuleExecutable(String name, RuleCategory category, Parameters parameters) {
        if (category == RuleCategory.DEFAULT) {  // Default rules can be executed
            return true;
        }

        String explicitRules = parameters.getExplicits();

        if (null == explicitRules || explicitRules.isEmpty()) {  // Ignore explicit rules since not specified
            return false;
        }

        return  explicitRules.contains(name);
    }
}
