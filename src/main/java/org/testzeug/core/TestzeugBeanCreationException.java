package org.testzeug.core;

class TestzeugBeanCreationException extends RuntimeException {

    TestzeugBeanCreationException(String message) {
        super("Cannot create Testzeug bean: " + message);
    }
    
}
