package com.dengpf.Lab.ThinkingInJava.ProxyTest1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by kobe73er on 16/12/3.
 */
public class DynamicProxy {
    interface SaySomething {

        void SayYes();

        void SayNo();
    }

    static class ConcreateClass implements SaySomething {

        @Override
        public void SayYes() {
            System.out.println("Concrete class say yes");
        }

        @Override
        public void SayNo() {
            System.out.println("Concrete class say no");
        }
    }

    static class MyInvocationHandler implements InvocationHandler {

        private Object target;

        public MyInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long startTime = System.currentTimeMillis();
            method.invoke(target, args);
            long endTime = System.currentTimeMillis();
            System.out.println(method + "execution time:" + (endTime = startTime));
            return null;
        }
    }

    public static void concreteRun(SaySomething saySomeThing) {
        saySomeThing.SayYes();
        saySomeThing.SayNo();
    }

    public static void main(String args[]) {
        SaySomething o = (SaySomething) Proxy.newProxyInstance(MyInvocationHandler.class.getClassLoader(), new Class[]{SaySomething.class}, new MyInvocationHandler(new ConcreateClass()));

        concreteRun(o);

    }
}
