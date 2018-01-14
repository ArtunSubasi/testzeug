package org.testzeug.core;

import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertNotNull;

public class TestzeugContextFactoryTest {

    private TestzeugContextFactory factory = new TestzeugContextFactory();

    @Test(expectedExceptions = TestzeugContextCreationException.class)
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

    @Test(expectedExceptions = TestzeugContextCreationException.class)
    public void createContextWithAnEmptyString() {
        factory.create(new ArrayList<>());
    }

    @Test(expectedExceptions = TestzeugContextCreationException.class)
    public void createContextWithListOfStrings() {
        List<String> yamlRoot = Collections.singletonList("A string does not belong in the root list.");
        factory.create(yamlRoot);
    }

    @Test(expectedExceptions = TestzeugContextCreationException.class)
    public void createContextWithListOfOneBeanWithoutId() {
        Map<String, Object> beanWithoutId = new HashMap<>();
        beanWithoutId.put("description", "This is a description!");
        List<Map<String, Object>> yamlRoot = Collections.singletonList(beanWithoutId);
        factory.create(yamlRoot);
    }

    @Test
    public void createContextWithListOfOneBeanWithId() {
        Map<String, Object> beanWithId = new HashMap<>();
        String testBeanId = "Martin";
        beanWithId.put("id", testBeanId);
        List<Map<String, Object>> yamlRoot = Collections.singletonList(beanWithId);

        TestzeugContext context = factory.create(yamlRoot);
        assertNotNull(context);

        TestzeugBean testzeugBean = context.getBean(testBeanId);
        assertNotNull(testzeugBean);
    }
    
}
