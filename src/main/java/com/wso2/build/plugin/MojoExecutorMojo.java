package com.wso2.build.plugin;

import com.wso2.build.beans.Rule;
import com.wso2.build.interfaces.Factory;
import com.wso2.build.interfaces.FactoryContainer;
import com.wso2.build.interfaces.PluginConfigParser;
import com.wso2.build.interfaces.RuleRegistry;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginExecution;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.PlexusContainerException;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.util.xml.Xpp3Dom;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static org.twdata.maven.mojoexecutor.MojoExecutor.*;

/**
 * Created by uvindra on 2/9/14.
 * @goal check
 */
public class MojoExecutorMojo extends AbstractMojo {
    /**
     * The project currently being build.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject;

    /**
     * The current Maven session.
     *
     * @parameter expression="${session}"
     * @required
     * @readonly
     */
    private MavenSession mavenSession;

    /**
     * The Maven BuildPluginManager component.
     *
     * @component
     * @required
     */
    private BuildPluginManager pluginManager;


    private FactoryContainer factoryContainer;


    @Override
    public void execute() throws MojoExecutionException {
        try {
            PlexusContainer container = new DefaultPlexusContainer();

            factoryContainer = container.lookup(FactoryContainer.class);

            Factory factory = factoryContainer.getFactory("default");

            RuleRegistry registry = factory.getRegistry();
            PluginConfigParser parser = factory.getParser();

            List<Rule> ruleList = registry.getRules();

            ListIterator<Rule> it = (ListIterator<Rule>) ruleList.iterator();

            while (it.hasNext()) {
                Rule rule = it.next();

                if (true != rule.isActive()) {
                    continue;
                }

                if (checkMavenCompatibility(rule.getCompatibleMavenVersion())) {

                    parser.parseConfigs(rule.getPluginUsage());

                    run(parser.getGroupId(), parser.getArtifactId(), parser.getVersion(), parser.getGoal(),
                            parser.getId(), parser.getConfiguration());
                }

            }

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
                executionEnvironment(mavenProject, mavenSession, pluginManager));
    }

    // TODO : implement this in a platform independent way
    private boolean checkMavenCompatibility(String mavenVersion) {
        return true;
    }
}
