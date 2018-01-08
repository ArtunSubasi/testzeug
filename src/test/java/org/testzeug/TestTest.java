package org.testzeug;

import org.testng.annotations.Test;

public class TestTest {
        
    @Test
    public void doSomething() {
        new ClassWithLogger().logSomething();
    }
    
}
