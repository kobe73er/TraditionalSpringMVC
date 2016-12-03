package com.dengpf.Lab.ThinkingInJava.ProxyTest1.transaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by kobe73er on 16/12/3.
 */
public class Client {


    public static void main(String args[]) {
        IBankService iBankService = (IBankService) Proxy.newProxyInstance(BankService.class.getClassLoader(), new Class[]{IBankService.class}, new MyInvocationHandler(new BankService(), new MyTransactionManager()));
//        iBankService.deposit();
        iBankService.withDraw();
//        iBankService.notTransactional();
    }


}
