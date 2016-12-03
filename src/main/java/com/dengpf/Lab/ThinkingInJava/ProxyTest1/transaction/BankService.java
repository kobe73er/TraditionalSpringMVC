package com.dengpf.Lab.ThinkingInJava.ProxyTest1.transaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by kobe73er on 16/12/3.
 */
public class BankService implements IBankService {

    private BankServiceDao bankServiceDao;

    public BankService() {
        bankServiceDao = new BankServiceDao();
    }

    @Override
    @Transactional
    public void deposit() {
        bankServiceDao.deposit();
    }

    @Override
    @Transactional
    public void withDraw() {
        bankServiceDao.withDraw();
    }

    @Override
    public void notTransactional() {
        System.out.println("I am not transactional so i am not executed!");
    }
}





