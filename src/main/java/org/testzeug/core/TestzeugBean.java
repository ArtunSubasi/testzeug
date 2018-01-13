package org.testzeug.core;

/**
 * Contains test data and meta data.
 *
 * @author Artun Subasi
 */
public class TestzeugBean {
    
    private final String id;

    TestzeugBean(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
}
