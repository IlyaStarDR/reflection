package com.company.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflection {

    //Метод принимает класс и возвращает созданный объект этого класса
    public static Object createInstanceReflection(Class clazz) {
        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }

    //Метод принимает object и вызывает у него все методы без параметров
    public static void invokeAllMethodsWithoutParams(Object object) {
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getParameterCount() == 0) {
                try {
                    method.setAccessible(true);
                    method.invoke(object);
                    System.out.println("\033[0;32m" + method + ": Invoked!" + "\033[0m");
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("\033[0;31m" + method + ": Failed!" + "\033[0m");
                }
            }
        }
    }

    //Метод принимает object и выводит на экран все сигнатуры методов в который есть final
    public static void printSignatureOfMethodsThatHaveFinal(Object object) {
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if ((method.getModifiers() & Modifier.FINAL) > 0) {
                System.out.println(method);
            }
        }
    }

    //Метод принимает Class и выводит все не публичные методы этого класса
    public static void printNotPublicMethodsOfClass(Class clazz) {
        Object object;
        try {
            object = clazz.newInstance();
            Method[] methods = object.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if ((method.getModifiers() & Modifier.PUBLIC) == 0) {
                    System.out.println(method);
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует
    public static void printClassAncestorsAndInterfacesImplemented(Class clazz) {
        try {
            Object object = clazz.newInstance();

            Object tempObject = object;
            while (tempObject != null) {
                tempObject = tempObject.getClass().getSuperclass();
                System.out.println(tempObject);
                if (tempObject.equals(Object.class)) {
                    tempObject = null;
                }
            }
            Class[] interfaces = object.getClass().getInterfaces();
            for (Class anInterface : interfaces) {
                System.out.println(anInterface);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //Метод принимает объект и меняет всего его приватные поля на их нулевые значение (null, 0, false etc)+
    public static void setAllPrivateFieldsToDefaultValues(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if ((field.getModifiers() == Modifier.PRIVATE)) {
                field.setAccessible(true);
                if (field.getType().isPrimitive()) {
                    if (field.getType().equals(boolean.class)) {
                        field.setBoolean(object, Boolean.FALSE);
                    } else if (field.getType().equals(char.class)) {
                        field.setChar(object, '\u0000');
                    } else if (field.getType().equals(double.class)) {
                        field.setDouble(object, 0.0d);
                    } else if (field.getType().equals(float.class)) {
                        field.setFloat(object, 0.0f);
                    } else if (field.getType().equals(long.class)) {
                        field.setLong(object, 0L);
                    } else if (field.getType().equals(int.class)) {
                        field.setInt(object, 0);
                    } else if (field.getType().equals(short.class)) {
                        field.setShort(object, (short) 0);
                    } else if (field.getType().equals(byte.class)) {
                        field.setByte(object, (byte) 0);
                    }
                }
                if (!field.getType().isPrimitive()) {
                    field.set(object, null);
                }
            }
        }
    }
}
