package org.example.homework24;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestRunner {
    public static void start(Class cl) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ExceptionTest {
        Object obj = cl.getDeclaredConstructor().newInstance();
        Method[] methods = cl.getDeclaredMethods();
        List<Method> beforeSuiteMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();
        List<Method> afterSuiteMethods = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                beforeSuiteMethods.add(method);
            } else if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                afterSuiteMethods.add(method);
            }
        }

        if (beforeSuiteMethods.size() > 1 || afterSuiteMethods.size() > 1) {
            throw new ExceptionTest("AllSuite unique");
        }

        beforeSuiteMethods.sort(Comparator.comparingInt((Method method) -> method.getAnnotation(BeforeSuite.class).hashCode()));
        afterSuiteMethods.sort(Comparator.comparingInt((Method method) -> method.getAnnotation(AfterSuite.class).hashCode()));

        beforeSuiteMethods.get(0).invoke(obj);

        testMethods.sort(Comparator.comparingInt((Method method) -> method.getAnnotation(Test.class).value()).thenComparing(Method::hashCode));

        for (Method method : testMethods) {
            method.invoke(obj);
        }

        afterSuiteMethods.get(0).invoke(obj);
    }
}

