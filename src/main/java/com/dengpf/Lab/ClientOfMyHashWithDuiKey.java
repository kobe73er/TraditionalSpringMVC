package com.dengpf.Lab;

/**
 * Created by kobe73er on 16/11/20.
 */
public class ClientOfMyHashWithDuiKey {

    public static void main(String args[]){
        MyHashMapWithDuiKey map = new MyHashMapWithDuiKey();
        map.put("keyOne","valueOne");
        map.put("keyOne","valueOneOne");

        map.put("keyTwo","valueTwo");


        System.out.println(map.getAll("keyOne"));

        System.out.println(map.size());

        System.out.println(map.containsValue("valueOne"));

    }
}
