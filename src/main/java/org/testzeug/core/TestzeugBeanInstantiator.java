package org.testzeug.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Instantiates java objects from Testzeug beans.
 *
 * @author Artun Subasi
 */
public class TestzeugBeanInstantiator {

    private final TestzeugBean bean;
    private boolean forcePrivateConstructorUsage;

    TestzeugBeanInstantiator(TestzeugBean bean) {
        this.bean = bean;
    }

    /**
     * Instantiates the bean and sets the simple fields 
     * without setting the {@link TestzeugBean} references or filling placeholders.
     */
    public Object instantiate() {
        try {
            Constructor<?> constructor = Class.forName(bean.getType()).getDeclaredConstructor();
            if (forcePrivateConstructorUsage) {
                constructor.trySetAccessible();
            }
            return constructor.newInstance();
        } catch (InstantiationException e) {
            throw new TestzeugContextCreationException("Could not create the Testzeug bean '" + bean.getId() +
                    "' because the class of the given type '" + bean.getType() + "' cannot be instantiated. " +
                    "Is it an interface or an abstract class?", e);
        } catch (IllegalAccessException e) {
            throw new TestzeugContextCreationException("Could not create the Testzeug bean '" + bean.getId() +
                    "' because the constructor of the given type '" + bean.getType() + "' is private.", e);
        } catch (InvocationTargetException e) {
            throw new TestzeugContextCreationException("Could not create the Testzeug bean '" + bean.getId() + 
                    "' because the constructor of the given type '" + bean.getType() + "' threw an exception.", e);
        } catch (NoSuchMethodException e) {
            throw new TestzeugContextCreationException("Could not create the Testzeug bean '" + bean.getId() +
                    "' because the given type '" + bean.getType() + "' does not have a no-argument constructor.", e);
        } catch (ClassNotFoundException e) {
            throw new TestzeugContextCreationException("Could not create the Testzeug bean '" + bean.getId() +
                    "' because no class could not be found for the given type '" + bean.getType() + "'.", e);
        }
    }

    public void forcePrivateConstructorUsage() {
        this.forcePrivateConstructorUsage = true;
    }
}
