package com.wso2.build.core;

import com.wso2.build.beans.Parameters;
import com.wso2.build.beans.Rule;
import com.wso2.build.enums.RuleCategory;
import com.wso2.build.enums.RuleType;
import com.wso2.build.interfaces.Factory;
import com.wso2.build.interfaces.PluginConfigParser;
import com.wso2.build.interfaces.RuleRegistry;
import com.wso2.build.scripting.ScriptUtilContext;
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
import org.xml.sax.InputSource;

import java.io.StringReader;
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
    private Log log = null;

    private final class RuleStat {
        public int inactiveCount = 0;
        public int excludedCount = 0;
        public int skippedExplicitCount = 0;
        public int mvnIncompatibleCount = 0;
        public int executedCount = 0;
    }

    public RuleExecutor(Log log) {
        this.log = log;
    }

    public void executeRulesSequentially(MavenProject project, MavenSession session, BuildPluginManager pluginManager,
                                RuntimeInformation runtime, Factory factory, Parameters parameters, List reactorProjects) throws MojoExecutionException {
        logRuleBegin(project.getName());

        RuleRegistry registry = factory.getRegistry(parameters);

        List<Rule> ruleList = registry.getRules();

        RuleStat stat = new RuleStat();

        for(Rule rule : ruleList) {

            if (true != rule.isActive()) {
                ++stat.inactiveCount;
                continue;
            }

            if (true == isRuleExcluded(rule.getName(), parameters)) {
                ++stat.excludedCount;
                continue;
            }

            if (true != isRuleExecutable(rule.getName(), rule.getCategory(), parameters)) {
                ++stat.skippedExplicitCount;
                continue;
            }

            if (RuleType.PLUGIN == rule.getType()) {
                if (true != isMavenCompatible(runtime, rule.getCompatibleMavenVersion())) {
                    ++stat.mvnIncompatibleCount;
                    continue;
                }

                executeMavenPluginRule(project, session, pluginManager, factory, rule, stat, ruleList.size());
            }
            else if (RuleType.SCRIPT == rule.getType()) {
                executeScriptRule(project, rule, stat, ruleList.size());
            }
            else {
                throw new MojoExecutionException("Unhandled Rule Type specified for rule");
            }
        }

        logRuleEnd(project.getName());

        // only print stats once, on the very last project in the reactor
        final int size = reactorProjects.size();
        MavenProject lastProject = (MavenProject) reactorProjects.get(size - 1);
        if (lastProject == project) {
            logRuleStats(ruleList.size(), stat);
        }
    }


    public void executeAllRules(MavenProject project, MavenSession session, BuildPluginManager pluginManager,
                                         RuntimeInformation runtime, Factory factory, Parameters parameters, List reactorProjects) throws MojoExecutionException {
        logRuleBegin(project.getName());

        RuleRegistry registry = factory.getRegistry(parameters);

        List<Rule> ruleList = registry.getRules();

        List<MojoExecutionException> exceptions = new LinkedList<MojoExecutionException>();

        RuleStat stat = new RuleStat();

        for (Rule rule : ruleList) {

            if (true != rule.isActive()) {
                ++stat.inactiveCount;
                continue;
            }

            if (true == isRuleExcluded(rule.getName(), parameters)) {
                ++stat.excludedCount;
                continue;
            }

            if (true != isRuleExecutable(rule.getName(), rule.getCategory(), parameters)) {
                ++stat.skippedExplicitCount;
                continue;
            }

            if (RuleType.PLUGIN == rule.getType()) {
                if (true != isMavenCompatible(runtime, rule.getCompatibleMavenVersion())) {
                    ++stat.mvnIncompatibleCount;
                    continue;
                }

                executeMavenPluginRule(project, session, pluginManager, factory, rule, stat, exceptions);
            }
            else if (RuleType.SCRIPT == rule.getType()) {
                executeScriptRule(project, rule, stat, exceptions);
            }
            else {
                exceptions.add(new MojoExecutionException("Unhandled Rule Type specified for rule"));
            }
        }

        for (MojoExecutionException exception : exceptions) {
            log.info(exception.getLocalizedMessage());
            log.info("======================================================");
            //throw exception;
        }
        logRuleEnd(project.getName());

        // only print stats once, on the very last project in the reactor
        final int size = reactorProjects.size();
        MavenProject lastProject = (MavenProject) reactorProjects.get(size - 1);
        if (lastProject == project) {
            logRuleStats(ruleList.size(), stat);
        }

        if (!exceptions.isEmpty()) {
            logRuleStats(ruleList.size(), stat);
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


    private void executeMavenPluginRule(MavenProject project, MavenSession session, BuildPluginManager pluginManager,
                                        Factory factory, Rule rule, RuleStat stat, List<MojoExecutionException> exceptions) {
        PluginConfigParser parser = factory.getParser();

        parser.parseConfigs(new InputSource(new StringReader(rule.getDefinition())));

        ++stat.executedCount;
        try {
            run(project, session, pluginManager, parser);
            logRulePassed(rule.getName());
        }
        catch (MojoExecutionException e) {
            logRuleFailed(rule.getName());
            exceptions.add(e);
        }
    }

    private void executeMavenPluginRule(MavenProject project, MavenSession session, BuildPluginManager pluginManager,
                                        Factory factory, Rule rule, RuleStat stat, final int ruleCount) throws MojoExecutionException {
        PluginConfigParser parser = factory.getParser();

        parser.parseConfigs(new InputSource(new StringReader(rule.getDefinition())));

        ++stat.executedCount;
        try {
            run(project, session, pluginManager, parser);
            logRulePassed(rule.getName());
        }
        catch (MojoExecutionException e) {
            logRuleFailed(rule.getName());
            logRuleStats(ruleCount, stat);
            throw e;
        }
    }

    private void executeScriptRule(MavenProject project, Rule rule, RuleStat stat, List<MojoExecutionException> exceptions) {
        ++stat.executedCount;

        ScriptUtilContext utils = new ScriptUtilContext(project);

        try {
            if (utils.exec(rule.getDefinition())) {
                logRulePassed(rule.getName());
            }
            else {
                logRuleFailed(rule.getName());
                exceptions.add(new MojoExecutionException(utils.getErrorString()));
            }
        } catch (MojoExecutionException e) {
            exceptions.add(e);
        }
    }

    private void executeScriptRule(MavenProject project, Rule rule, RuleStat stat, final int ruleCount) throws MojoExecutionException {
        ++stat.executedCount;

        ScriptUtilContext utils = new ScriptUtilContext(project);

        if (utils.exec(rule.getDefinition())) {
            logRulePassed(rule.getName());
        }
        else {
            logRuleFailed(rule.getName());
            logRuleStats(ruleCount, stat);
            throw new MojoExecutionException(utils.getErrorString());
        }
    }


    private void logRuleBegin(final String name) {
        log.info("=================================================================================");
        log.info("BEGIN RULE CHECK FOR : " + name);
        log.info("=================================================================================");
    }

    private void logRuleEnd(final String name) {
        log.info("=================================================================================");
        log.info("END RULE CHECK FOR : " + name);
        log.info("=================================================================================");

    }

    private void logRulePassed(final String name) {
        log.info("Rule Name : " + name + ", Status : PASSED");
    }

    private void logRuleFailed(final String name) {
        log.info("Rule Name : " + name + ", Status : FAILED");
    }


    private void logRuleStats(final int totalRuleCount, final RuleStat stat) {
        log.info("=================================================================================");
        log.info("RULE EXECUTION STATS");
        log.info("=================================================================================");
        log.info("Total Rule Count : " + totalRuleCount);
        log.info("Executed Rule Count : " + stat.executedCount);
        log.info("Inactive Rule Count : " + stat.inactiveCount);
        log.info("Excluded Rule Count : " + stat.excludedCount);
        log.info("Maven Version Incompatible Count : " + stat.mvnIncompatibleCount);
        log.info("Skipped Explicit Rule Count : " + stat.skippedExplicitCount);
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
