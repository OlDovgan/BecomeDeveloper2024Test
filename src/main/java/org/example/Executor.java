package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Executor {
    public void executeMethod(Object obj, String methodName) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Method method = obj.getClass().getMethod(methodName);
        long startTime = System.nanoTime();
        Object result = method.invoke(obj);
        long endTime = System.nanoTime();
        System.out.println("Execution time of " + methodName + " " +
                (endTime - startTime) / 1_000_000 + " milliseconds");
        System.out.println(methodName.substring(4) + " = "+ result) ;
    }
}