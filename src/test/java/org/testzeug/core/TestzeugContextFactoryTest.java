package org.testzeug.core;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertNotNull;

public class TestzeugContextFactoryTest {

    private TestzeugContextFactory factory = new TestzeugContextFactory();

    @Test(expectedExceptions = TestzeugBeanCreationException.class)
    public void createContextWithOneBeanWithoutId() {
        Map<String, Object> yamlRoot = new HashMap<>();
        yamlRoot.put("description", "This is a description!");
        factory.create(yamlRoot);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createContextWithIllegalArgument() {
        factory.create("A string is sadly not enough to create a Testzeug context.");
    }
    
    @Test
    public void createContextWithOneBeanWIthId() {
        Map<String, Object> yamlRoot = new HashMap<>();
        String testBeanId = "Martin";
        yamlRoot.put("id", testBeanId);

        TestzeugContext context = factory.create(yamlRoot);
        assertNotNull(context);
        
        TestzeugBean testzeugBean = context.getBean(testBeanId);
        assertNotNull(testzeugBean);
    }
    
}
