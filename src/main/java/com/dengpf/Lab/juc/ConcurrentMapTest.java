package com.dengpf.Lab.juc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by kobe73er on 16/11/21.
 */
public class ConcurrentMapTest {
    private static long SBASE;

    private static sun.misc.Unsafe UNSAFE;


    public static void main(String args[]) {
        ConcurrentMap cMap = new ConcurrentHashMap();

//
//        System.out.println(Integer.parseInt("0001111", 2) & 15);
//        System.out.println(Integer.parseInt("0011111", 2) & 15);
//        System.out.println(Integer.parseInt("0111111", 2) & 15);
//        System.out.println(Integer.parseInt("1111111", 2) & 15);

//        System.out.println(Integer.parseInt("0011111", 2));
//
//
//        UNSAFE = sun.misc.Unsafe.getUnsafe();
//
//        System.out.println(UNSAFE.arrayBaseOffset(ConcurrentMapTest.class));
      numberOfLeadingZeros(65536);


//        System.out.println(a>>2&1);

    }

    public static int numberOfLeadingZeros(int i) {
        // HD, Figure 5-6
        if (i == 0)
            return 32;
        int n = 1;
        if (i >>> 16 == 0) {
            n += 16;
            i <<= 16;
        }
        if (i >>> 24 == 0) {
            n += 8;
            i <<= 8;
        }
        if (i >>> 28 == 0) {
            n += 4;
            i <<= 4;
        }
        if (i >>> 30 == 0) {
            n += 2;
            i <<= 2;
        }
        n -= i >>> 31;
        return n;
    }
}
