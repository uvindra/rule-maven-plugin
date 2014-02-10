package com.wso2.build.parser;

import junit.framework.TestCase;
import org.junit.Test;
import org.xml.sax.InputSource;

import java.io.StringReader;

/**
 * Created by uvindra on 2/9/14.
 */
public class SAXConfigParserTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();




    }

    public void tearDown() throws Exception {

    }

    @Test
    public void testParsing() {
        String xmlString = "<plugin>\n" +
                "        <groupId>org.apache.maven.plugins</groupId>\n" +
                "        <artifactId>maven-enforcer-plugin</artifactId>\n" +
                "        <version>1.3.1</version>\n" +
                "        <executions>\n" +
                "          <execution>\n" +
                "            <id>enforce-banned-dependencies</id>\n" +
                "            <goals>\n" +
                "              <goal>enforce</goal>\n" +
                "            </goals>\n" +
                "            <configuration>\n" +
                "              <rules>\n" +
                "                <banTransitiveDependencies>\n" +
                "                </banTransitiveDependencies>\n" +
                "              </rules>\n" +
                "            </configuration>\n" +
                "          </execution>\n" +
                "        </executions>\n" +
                "</plugin>";

        InputSource source = new InputSource(new StringReader(xmlString));

        SAXConfigParser parse = new SAXConfigParser();
        parse.parseConfigs(source);
    }
}
