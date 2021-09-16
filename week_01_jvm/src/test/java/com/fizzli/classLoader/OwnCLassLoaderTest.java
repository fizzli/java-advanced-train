package com.fizzli.classLoader;

import com.fizzli.classloader.OwnClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OwnCLassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        OwnClassLoader loader = new OwnClassLoader();
        Class<?> hello = loader.loadClass("Hello");
        try {
            Object obj = hello.getDeclaredConstructor().newInstance();
            Method helloMethod = obj.getClass().getDeclaredMethod("hello");
            helloMethod.invoke(obj);


        }catch (NoSuchMethodException | InvocationTargetException |
                InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
    }
}
