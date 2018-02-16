package org.testzeug.core;

public class PojoWithFaultyConstructor {
    
    public PojoWithFaultyConstructor() {
        throw new RuntimeException("Oops! could not construct!");
    }
    
}
