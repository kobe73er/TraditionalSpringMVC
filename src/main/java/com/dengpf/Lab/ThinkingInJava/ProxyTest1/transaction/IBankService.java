package com.dengpf.Lab.ThinkingInJava.ProxyTest1.transaction;

/**
 * Created by kobe73er on 16/12/3.
 */
public interface IBankService {

    void deposit();

    void withDraw();

    void notTransactional();

}
