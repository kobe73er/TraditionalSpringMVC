package com.dengpf.Lab.ThinkingInJava.ProxyTest1.transaction;

import javax.transaction.*;

/**
 * Created by kobe73er on 16/12/3.
 */
public class MyTransactionManager implements TransactionManager {
    @Override
    public void begin() throws NotSupportedException, SystemException {
        System.out.println("transaction begin");

    }

    @Override
    public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        System.out.println("transactioin commit");
    }

    @Override
    public int getStatus() throws SystemException {
        return 0;
    }

    @Override
    public Transaction getTransaction() throws SystemException {
        return null;
    }

    @Override
    public void resume(Transaction tobj) throws InvalidTransactionException, IllegalStateException, SystemException {
        System.out.println("transaction resume");
    }

    @Override
    public void rollback() throws IllegalStateException, SecurityException, SystemException {
        System.out.println("Exception happens hence rollback!");
    }

    @Override
    public void setRollbackOnly() throws IllegalStateException, SystemException {

    }

    @Override
    public void setTransactionTimeout(int seconds) throws SystemException {

    }

    @Override
    public Transaction suspend() throws SystemException {
        return null;
    }
}
