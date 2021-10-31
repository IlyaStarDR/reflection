package com.company.reflection;

import com.company.junit.Test;

import java.util.Random;

import static com.company.reflection.Reflection.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReflectionTest {

    @Test
    public void testCreateInstanceReflection() {
        Random random = (Random) createInstanceReflection(Random.class);
        assertNotNull(random);
    }

    @Test
    public void testInvokeAllMethodsWithoutParams() {
        Random random = (Random) createInstanceReflection(Random.class);
        assertNotNull(random);
        invokeAllMethodsWithoutParams(random);
    }

    @Test
    public void testPrintSignatureOfMethodsThatHaveFinal() {
        Random random = (Random) createInstanceReflection(Random.class);
        assertNotNull(random);
        printSignatureOfMethodsThatHaveFinal(random);
    }

    @Test
    public void testPrintNotPublicMethodsOfClass() {
        printNotPublicMethodsOfClass(Random.class);
    }

    @Test
    public void testPrintClassAncestorsAndInterfacesImplemented() {
        printClassAncestorsAndInterfacesImplemented(Random.class);
    }

    @Test
    public void testSetAllPrivateFieldsToDefaultValues() throws IllegalAccessException {
        Random random = (Random) createInstanceReflection(Random.class);
        assertNotNull(random);
        setAllPrivateFieldsToDefaultValues(random);
    }

}