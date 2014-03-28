package com.wso2.build.core;

import com.wso2.build.beans.Parameters;
import com.wso2.build.beans.Rule;
import com.wso2.build.enums.RuleCategory;
import com.wso2.build.interfaces.Factory;
import com.wso2.build.interfaces.PluginConfigParser;
import com.wso2.build.interfaces.RuleRegistry;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginExecution;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.apache.maven.rtinfo.RuntimeInformation;
import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Settings;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.twdata.maven.mojoexecutor.MojoExecutor.executeMojo;
import static org.twdata.maven.mojoexecutor.MojoExecutor.executionEnvironment;

/**
 * Created by uvindra on 3/4/14.
 */
public final class RuleExecutor {
    Log log = null;

    public RuleExecutor(Log log) {
        this.log = log;
    }

    public void executeRulesSequentially(MavenProject project, MavenSession session, BuildPluginManager pluginManager,
                                RuntimeInformation runtime, Factory factory, Parameters parameters) throws MojoExecutionException {
        logRuleBegin(project.getName());

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

            if (isMavenCompatible(runtime, rule.getCompatibleMavenVersion())) {
                PluginConfigParser parser = factory.getParser();

                parser.parseConfigs(rule.getPluginUsage());

                try {
                    run(project, session, pluginManager, parser);
                    logRulePassed(rule.getName());
                }
                catch (MojoExecutionException e) {
                    logRuleFailed(rule.getName());
                    throw e;
                }
            }
        }

        logRuleEnd(project.getName());
    }


    public void executeAllRules(MavenProject project, MavenSession session, BuildPluginManager pluginManager,
                                         RuntimeInformation runtime, Factory factory, Parameters parameters) throws MojoExecutionException {
        logRuleBegin(project.getName());

        RuleRegistry registry = factory.getRegistry(parameters);

        List<Rule> ruleList = registry.getRules();

        List<MojoExecutionException> exceptions = new LinkedList<MojoExecutionException>();

        for (Rule rule : ruleList) {

            if (true != rule.isActive()) {
                continue;
            }

            if (true == isRuleExcluded(rule.getName(), parameters)) {
                continue;
            }

            if (true != isRuleExecutable(rule.getName(), rule.getCategory(), parameters)) {
                continue;
            }

            if (isMavenCompatible(runtime, rule.getCompatibleMavenVersion())) {
                PluginConfigParser parser = factory.getParser();

                parser.parseConfigs(rule.getPluginUsage());

                try {
                    run(project, session, pluginManager, parser);
                    logRulePassed(rule.getName());
                }
                catch (MojoExecutionException e) {
                    logRuleFailed(rule.getName());
                    exceptions.add(e);
                }
            }
        }

        for (MojoExecutionException exception : exceptions) {
            log.info(exception.getLocalizedMessage());
            log.info("======================================================");
            //throw exception;
        }
        logRuleEnd(project.getName());

        if (!exceptions.isEmpty()) {
             throw new MojoExecutionException("Rule failure detected");
        }
    }


    public Parameters loadParameters(Settings settings) {
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


    private void logRuleBegin(String name) {
        log.info("=================================================================================");
        log.info("BEGIN RULE CHECK FOR : " + name);
        log.info("=================================================================================");
    }

    private void logRuleEnd(String name) {
        log.info("=================================================================================");
        log.info("END RULE CHECK FOR : " + name);
        log.info("=================================================================================");

    }

    private void logRulePassed(String name) {
        log.info("Rule Name : " + name + ", Status : PASSED");
    }

    private void logRuleFailed(String name) {
        log.info("Rule Name : " + name + ", Status : FAILED");
    }


    private void run(MavenProject project, MavenSession session, BuildPluginManager pluginManager,
                                PluginConfigParser parser) throws MojoExecutionException{
        Plugin plugin = new Plugin();
        plugin.setGroupId(parser.getGroupId());
        plugin.setArtifactId(parser.getArtifactId());
        plugin.setVersion(parser.getVersion());

        PluginExecution pluginExecution = new PluginExecution();

        pluginExecution.setId(parser.getId());

        List<String> goals = new LinkedList<String>();
        goals.add(parser.getGoal());
        pluginExecution.setGoals(goals);

        plugin.addExecution(pluginExecution);

        executeMojo(plugin, parser.getGoal(), parser.getConfiguration(),
                executionEnvironment(project, session, pluginManager));
    }


    private boolean isMavenCompatible(RuntimeInformation runtime, String mavenVersion) {
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
