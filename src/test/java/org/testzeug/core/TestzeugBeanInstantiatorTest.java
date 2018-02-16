package org.testzeug.core;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class TestzeugBeanInstantiatorTest {

    @Test
    public void instantiate_oneStringPojo() {
        TestzeugBean bean = new TestzeugBean("first");
        Map<String, Object> data = new HashMap<>();
        data.put("string", "Test!");
        bean.setType(OneStringPojo.class.getName());
        bean.setData(data);
        
        TestzeugBeanInstantiator instantiator = new TestzeugBeanInstantiator(bean);

        Object testObject = instantiator.instantiate();
        assertNotNull(testObject);
        assertTrue(testObject instanceof OneStringPojo);
    }

    @Test(expectedExceptions = TestzeugContextCreationException.class,
            expectedExceptionsMessageRegExp = "Could not create the Testzeug bean 'first' because no class " +
                    "could not be found for the given type 'Nope!'.")
    public void instantiate_classNotFound() {
        TestzeugBean bean = new TestzeugBean("first");
        bean.setType("Nope!");

        new TestzeugBeanInstantiator(bean).instantiate();
    }

    // TODO no-arg constructors should be supported in the future using the -parameters flag of the compiler
    @Test(expectedExceptions = TestzeugContextCreationException.class,
            expectedExceptionsMessageRegExp = "Could not create the Testzeug bean 'first' because the given type " +
                    "'org.testzeug.core.PojoWithoutNoArgumentConstructor' does not have a no-argument constructor.")
    public void instantiate_constructorNotFound() {
        TestzeugBean bean = new TestzeugBean("first");
        bean.setType(PojoWithoutNoArgumentConstructor.class.getName());

        new TestzeugBeanInstantiator(bean).instantiate();
    }

    @Test(expectedExceptions = TestzeugContextCreationException.class,
            expectedExceptionsMessageRegExp = "Could not create the Testzeug bean 'first' because the constructor " +
                    "of the given type 'org.testzeug.core.PojoWithFaultyConstructor' threw an exception.")
    public void instantiate_constructorThrowsException() {
        TestzeugBean bean = new TestzeugBean("first");
        bean.setType(PojoWithFaultyConstructor.class.getName());

        new TestzeugBeanInstantiator(bean).instantiate();
    }

    @Test(expectedExceptions = TestzeugContextCreationException.class,
            expectedExceptionsMessageRegExp = "Could not create the Testzeug bean 'first' because the class " +
                    "of the given type 'org.testzeug.core.AbstractPojo' cannot be instantiated. " +
                    "Is it an interface or an abstract class\\?")
    public void instantiate_abstractClass() {
        TestzeugBean bean = new TestzeugBean("first");
        bean.setType(AbstractPojo.class.getName());

        new TestzeugBeanInstantiator(bean).instantiate();
    }

    @Test(expectedExceptions = TestzeugContextCreationException.class,
            expectedExceptionsMessageRegExp = "Could not create the Testzeug bean 'first' because the constructor " +
                    "of the given type 'org.testzeug.core.PojoWithPrivateConstructor' is private.")
    public void instantiate_privateConstructor() {
        TestzeugBean bean = new TestzeugBean("first");
        bean.setType(PojoWithPrivateConstructor.class.getName());

        new TestzeugBeanInstantiator(bean).instantiate();
    }

    @Test
    public void instantiate_privateConstructor_forcePrivateConstructorUsage() {
        TestzeugBean bean = new TestzeugBean("first");
        bean.setType(PojoWithPrivateConstructor.class.getName());

        TestzeugBeanInstantiator instantiator = new TestzeugBeanInstantiator(bean);
        instantiator.forcePrivateConstructorUsage();

        Object testObject = instantiator.instantiate();
        assertNotNull(testObject);
        assertTrue(testObject instanceof PojoWithPrivateConstructor);
    }
    
}