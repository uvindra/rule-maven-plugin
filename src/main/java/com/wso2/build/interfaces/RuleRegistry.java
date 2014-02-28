package com.wso2.build.interfaces;

import com.wso2.build.beans.Rule;
import org.apache.maven.plugin.MojoExecutionException;

import java.util.List;

/**
 * Created by uvindra on 2/8/14.
 */
public interface RuleRegistry {
    List<Rule> getRules() throws MojoExecutionException;
}
