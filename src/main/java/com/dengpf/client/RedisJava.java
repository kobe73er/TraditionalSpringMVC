package com.dengpf.client;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisJava {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: " + jedis.ping());

        jedis.set("name", "dengpf");

        System.out.println(jedis.get("name"));

        //存储数据到列表中
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");

        // 获取存储的数据并输出
        List<String> list = jedis.lrange("tutorial-list", 0, 5);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Stored string in redis:: " + list.get(i));
        }

        System.out.println("--------------redis keys-------------------");

        Set<String> redisKeysSet = jedis.keys("*");

        Iterator<String> iterator = redisKeysSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}