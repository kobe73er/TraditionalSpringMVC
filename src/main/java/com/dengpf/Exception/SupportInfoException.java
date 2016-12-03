package com.dengpf.Exception;

/**
 * Created by kobe73er on 16/11/3.
 */
public class SupportInfoException extends Exception {

    /**
     * Unique ID for Serialized object
     */
    private static final long serialVersionUID = 4657491283614755649L;

    public SupportInfoException(String msg) {
        super(msg);
    }

    public SupportInfoException(String msg, Throwable t) {
        super(msg, t);
    }

}
