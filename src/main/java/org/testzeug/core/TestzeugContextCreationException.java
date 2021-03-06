package org.testzeug.core;

class TestzeugContextCreationException extends RuntimeException {

    TestzeugContextCreationException(String message) {
        super("Could not create the Testzeug context: " + message);
    }

    TestzeugContextCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
