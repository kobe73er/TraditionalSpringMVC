package com.dengpf.Lab.juc;

/**
 * Created by kobe73er on 16/11/17.
 */
public class TestShift {
    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;


    /** Returns the number of shared holds represented in count  */
    static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }
    /** Returns the number of exclusive holds represented in count  */
    static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }


    public static void main(String args[]) {

        System.out.println(SHARED_SHIFT);
        System.out.println(SHARED_UNIT);
        System.out.println(MAX_COUNT);
        System.out.println(EXCLUSIVE_MASK);

        System.out.println(exclusiveCount(3334));

        System.out.println("sharedCount==>"+sharedCount(229376));

    }
}
