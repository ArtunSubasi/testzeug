package org.testzeug.parser.yaml;

import org.testng.annotations.Test;
import org.testzeug.core.TestzeugBeanAttributes;
import org.testzeug.test.TestUtil;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class SnakeYamlParserTest {
    
    @Test
    public void parseListWithOnlyIds() throws Exception {
        String yamlWithIds = TestUtil.resourceToString("yaml/ListWithOnlyIds.yml");
        List yamlRoot = (List) new SnakeYamlParser().parse(yamlWithIds);
        assertNotNull(yamlRoot);
        assertEquals(2, yamlRoot.size());
        
        Map firstMap = (Map) yamlRoot.get(0);
        assertNotNull(firstMap);
        assertEquals("Martin", firstMap.get(TestzeugBeanAttributes.ID));

        Map secondMap = (Map) yamlRoot.get(1);
        assertNotNull(secondMap);
        assertEquals("Kiddy", secondMap.get(TestzeugBeanAttributes.ID));
    }


    @Test
    public void parseSingleObjectWIthOnlyId() throws Exception {
        String yamlMartin = TestUtil.resourceToString("yaml/SingleObjectWIthOnlyId.yml");
        Map yamlRoot = (Map) new SnakeYamlParser().parse(yamlMartin);

        assertNotNull(yamlRoot);
        assertEquals("Martin", yamlRoot.get(TestzeugBeanAttributes.ID));
    }

}