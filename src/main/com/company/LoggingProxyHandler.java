package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class LoggingProxyHandler<T> implements InvocationHandler {
    private T target;

    public LoggingProxyHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        File file = new File("log.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file,true);
            fr.write(new Date().toString() + " invoke method " + method.getName() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return method.invoke(target, args);
    }
}
