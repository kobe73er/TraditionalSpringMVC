package com.dengpf.Lab.set;

import com.dengpf.Lab.MyHashMap;

import java.util.Iterator;

/**
 * Created by kobe73er on 16/11/21.
 */
public class ClientTwo {

    public static void main(String args[]) {


        MyHashSetUsingHashSet mset = new MyHashSetUsingHashSet(new MyHashMap());

        mset.add("one");
        mset.add("one");
        mset.add("two");


        System.out.println(mset.size());

        Iterator it = mset.iterator();

        while (it.hasNext()) {
            String s = (String) it.next();
            System.out.println(s);
            if (s.equals("two")) {
                System.out.println("remove:" + s);
                it.remove();
            }
        }

    }
}
