package org.testzeug;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ClassWithLogger {
    
    private static final Logger LOGGER = LogManager.getLogger(ClassWithLogger.class);
    
    static void logSomething() {
        LOGGER.info("The world was on fire and noone could save me but Testzeug!");
    }
    
}
