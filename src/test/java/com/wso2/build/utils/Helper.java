package com.wso2.build.utils;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by uvindra on 2/26/14.
 */
public class Helper {

    public static MavenProject getTestParentProject(URL url) {
        Model model = null;
        MavenXpp3Reader reader = new MavenXpp3Reader();

        try {
            model = reader.read(url.openStream());
            File pomFile = new File(url.getFile());
            model.setPomFile(pomFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        MavenProject mavenProject = new MavenProject(model);

        return mavenProject;
    }

    public static MavenProject getTestChildProject(URL parentURL, URL childURL) {
        Model model = null;
        MavenXpp3Reader reader = new MavenXpp3Reader();

        try {
            model = reader.read(childURL.openStream());
            File pomFile = new File(childURL.getFile());
            model.setPomFile(pomFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        MavenProject childProject = new MavenProject(model);

        MavenProject parentProject = getTestParentProject(parentURL);

        if (null != parentProject) {
            childProject.setParent(parentProject);
        }
        else {
            childProject = null;
        }

        return childProject;
    }
}
