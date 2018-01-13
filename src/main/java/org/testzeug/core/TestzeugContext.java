package org.testzeug.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains the Testzeug beans and provides access to the Testzeug beans.
 * 
 * @author Artun Subasi
 */
public class TestzeugContext {
    
    private Map<String, TestzeugBean> beans = new HashMap<>(); 
    
    public TestzeugBean getBean(final String id) {
        return beans.get(id);
    }
    
    void addBean(TestzeugBean bean) {
        beans.put(bean.getId(), bean);
    } 
    
}
