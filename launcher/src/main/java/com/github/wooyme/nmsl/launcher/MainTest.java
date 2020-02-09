package com.github.wooyme.nmsl.launcher;

import java.lang.reflect.Method;

public class MainTest {
    public static void main(String[] args) {
        Class clazz = java.sql.SQLSyntaxErrorException.class;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getDeclaringClass().getName()+":"+method.getName());

        }
    }
}
