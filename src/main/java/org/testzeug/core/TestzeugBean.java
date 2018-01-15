package org.testzeug.core;

/**
 * Contains test data and meta data.
 *
 * @author Artun Subasi
 */
public class TestzeugBean {
    
    private final String id;
    private String type;

    TestzeugBean(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }
    
}
