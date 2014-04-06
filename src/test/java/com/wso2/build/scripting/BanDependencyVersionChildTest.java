package com.wso2.build.scripting;

import com.wso2.build.utils.Helper;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.net.URL;

/**
 * Created by uvindra on 4/2/14.
 */
public class BanDependencyVersionChildTest extends TestCase {
    @Test
    public void testDependencyVersionNotInChild() throws Exception {
        URL parentPomURL = this.getClass().getResource("/dependency_version/parent_pom.xml");
        URL childPomURL = this.getClass().getResource("/dependency_version/child/carbon_dependency_version_in_child.xml");

        URL scriptURL = this.getClass().getResource("/dependency_version/script.js");

        String scriptFile = FileUtils.readFileToString(new File(scriptURL.getFile()));

        ScriptUtilContext utils = new ScriptUtilContext(Helper.getTestChildProject(parentPomURL, childPomURL));

        Assert.assertEquals(true, utils.exec(scriptFile));
    }
}
