package org.testzeug.core;

import java.util.Map;

/**
 * Creates the {@link org.testzeug.core.TestzeugContext} from a YAML input which parsed as a {@link java.util.List} 
 * or a {@link java.util.Map}.
 *
 * @author Artun Subasi
 */
class TestzeugContextFactory {

    public TestzeugContext create(Object yamlRoot) {
        TestzeugContext context = new TestzeugContext();
        if (yamlRoot instanceof Map) {
            context.addBean(createBeanFromMap((Map) yamlRoot));
        } else {
            throw new IllegalArgumentException("Cannot create a Testzeug context from a " + yamlRoot.getClass());
        }
        return context;
    }

    private TestzeugBean createBeanFromMap(Map yamlMap) {
        Object id = yamlMap.get(TestzeugBeanAttributes.ID);
        if (id == null) {
            throw new TestzeugBeanCreationException("id not defined. Yaml-Node: " + yamlMap);
        }
        return new TestzeugBean(id.toString());
    }

}
