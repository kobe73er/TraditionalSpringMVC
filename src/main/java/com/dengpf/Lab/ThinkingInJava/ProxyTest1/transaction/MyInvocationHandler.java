package com.dengpf.Lab.ThinkingInJava.ProxyTest1.transaction;

import javax.transaction.TransactionManager;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;

/**
 * Created by kobe73er on 16/12/3.
 */

public class MyInvocationHandler implements InvocationHandler {
    private Object target;
    private TransactionManager transactionManager;


    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    public MyInvocationHandler(Object target, TransactionManager transactionManager) {
        this.target = target;
        this.transactionManager = transactionManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("executing proxy");
        Method originalMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        if (!originalMethod.isAnnotationPresent(Transactional.class)) {
            System.out.println("nontransctional method executed!");
            return method.invoke(target, args);
        }
        transactionManager.begin();
        try {
            System.out.println("trasactional method executed!");
            method.invoke(target, args);
            int r = 1 / 0;
            transactionManager.commit();
        } catch (Exception ex) {
            transactionManager.rollback();
        } finally {
            //close this transactionManager
        }
        return null;
    }
}