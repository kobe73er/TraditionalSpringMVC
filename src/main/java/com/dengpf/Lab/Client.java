package com.dengpf.Lab;

/**
 * Created by kobe73er on 16/11/19.
 */
public class Client {
    public static void main(String args[]) {
        MyHashMap myHashMap = new MyHashMap();

        myHashMap.put("keyOne","valueOne");
        myHashMap.put("keyTwo","valueTwo");

        myHashMap.clear();

        System.out.println(myHashMap.isEmpty());

        System.out.println(myHashMap.get("keyOne"));

        System.out.println(myHashMap.containsKey("keyOne"));

        System.out.println(myHashMap.containsValue("valueOne"));

        System.out.println(myHashMap.size());


    }
}
