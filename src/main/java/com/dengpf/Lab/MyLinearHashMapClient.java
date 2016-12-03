package com.dengpf.Lab;

import java.util.Iterator;

/**
 * Created by kobe73er on 16/11/19.
 */
public class MyLinearHashMapClient<K, V> {


    public static void main(String args[]) {
        MyLinearHashMap myMap = new MyLinearHashMap();
        myMap.put("keyOne", "valueOne");
        myMap.put("keyTwo","keyTwo");
        myMap.put("keyOne", "valueOne");
        myMap.put("keyTwo","keyTwo");
        myMap.put("keyOne", "valueOne");
        myMap.put("keyTwo","keyTwo");
        myMap.put("keyOne", "valueOne");
        myMap.put("keyTwo","keyTwo");
        myMap.put("keyOne", "valueOne");
        myMap.put("keyTwo","keyTwo");

        System.out.println(myMap.isEmpty());
        System.out.println(myMap.containsKey("keyOne"));

        System.out.println(myMap.containsValue("valueOne"));

        System.out.println(myMap.get("keyOne"));

        System.out.println(myMap.size());

        Iterator it=myMap.keySet().iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }


}
