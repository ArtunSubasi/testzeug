package org.testzeug.core;

import java.util.List;
import java.util.Map;

/**
 * Creates the {@link org.testzeug.core.TestzeugContext} from a YAML input which parsed as a {@link java.util.List} 
 * or a {@link java.util.Map}. This is an internal, not thread-safe class.
 *
 * @author Artun Subasi
 */
class TestzeugContextFactory {
    
    private TestzeugContext context;

    public TestzeugContext create(Object yamlRoot) {
        context = new TestzeugContext();
        if (yamlRoot instanceof Map) {
            context.addBean(createBeanFromMap((Map) yamlRoot));
        } else if (yamlRoot instanceof List) {
            addBeansFromRootList((List) yamlRoot);
        } else {
            throw new IllegalArgumentException("Cannot create a Testzeug context from a " + yamlRoot.getClass());
        }
        return context;
    }

    private static TestzeugBean createBeanFromMap(Map yamlMap) {
        TestzeugBean testzeugBean = initBean(yamlMap);
        fillType(yamlMap, testzeugBean);
        fillData(yamlMap, testzeugBean);
        return testzeugBean;
    }

    private static TestzeugBean initBean(Map yamlMap) {
        Object id = yamlMap.get(TestzeugBeanAttributes.ID);
        if (id == null) {
            throw new TestzeugContextCreationException("A Testzeug bean does not contain an id: " + yamlMap);
        }
        return new TestzeugBean(id.toString());
    }

    private static void fillType(Map yamlMap, TestzeugBean testzeugBean) {
        Object type = yamlMap.get(TestzeugBeanAttributes.TYPE);
        if (type != null) {
            testzeugBean.setType(type.toString());
        }
    }

    private static void fillData(Map yamlMap, TestzeugBean testzeugBean) {
        Object data = yamlMap.get(TestzeugBeanAttributes.DATA);
        if (data instanceof Map) {
            testzeugBean.setData(data);
        }
    }

    private void addBeansFromRootList(List yamlRoot) {
        if (yamlRoot.isEmpty()) {
            throw new TestzeugContextCreationException("The root list is empty.");
        }
        for (Object yamlElement : yamlRoot) {
            if (yamlElement instanceof Map) {
                context.addBean(createBeanFromMap((Map) yamlElement));
            } else {
                throw new TestzeugContextCreationException("The root list contains an unexpected type"
                        + yamlElement.getClass() + ". Was expected a Testzeug bean with an id.");
            }
        }
    }

}
