package com.wso2.build.scripting;

import com.wso2.build.beans.Parameters;
import com.wso2.build.utils.Helper;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.apache.maven.model.Build;
import org.apache.maven.model.Plugin;
import org.apache.maven.monitor.logging.DefaultLog;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.logging.console.ConsoleLogger;
import org.junit.Test;

import java.io.File;
import java.net.URL;

/**
 * Created by uvindra on 4/9/14.
 */
public class EliminateBlindImportsTest extends TestCase {
    @Test
    public void testEliminateBlindImports() throws Exception {
        URL pomURL = this.getClass().getResource("/blind_imports/test_pom.xml");

        URL scriptURL = this.getClass().getResource("/blind_imports/Eliminate_blindly_imported_bundles.js");

        String scriptFile = FileUtils.readFileToString(new File(scriptURL.getFile()));

        /*
        Parameters parameters = new Parameters();
        parameters.setHomePath("/home/uvindra/test_rule_home");
        parameters.setPackageEndpoint("https://ec2-54-83-33-151.compute-1.amazonaws.com:9443/admin/services/OSGiService");
        parameters.setRegistryUsername("admin");
        parameters.setRegistryPassword("admin");
        parameters.setTrustStorePassword("wso2carbon");

        MavenProject project = Helper.getTestParentProject(pomURL);

        ConsoleLogger logger = new ConsoleLogger(1, "Test");
        DefaultLog log = new DefaultLog(logger);

        ScriptUtilContext utils = new ScriptUtilContext(project, parameters, log);

        Assert.assertEquals(true, utils.exec(scriptFile));
        */
    }
}
