package com.company.junit;

import com.company.reflection.ReflectionTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args) {
        invokeTest(ReflectionTest.class);
    }

    public static void invokeTest(Class clazz) {
        Object testClass;

        try {
            testClass = clazz.newInstance();
            Method[] methods = testClass.getClass().getDeclaredMethods();
            for (Method method : methods) {
                method.setAccessible(true);
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().getSimpleName().equals("Test")) {
                        System.out.println("\033[1;33m" + "Invocation of " + method.getName() + " STARTED" + "\033[0m");
                        method.invoke(testClass);
                        System.out.println("\033[1;33m" + "Invocation of " + method.getName() + " SUCCESS" + "\033[0m");
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
