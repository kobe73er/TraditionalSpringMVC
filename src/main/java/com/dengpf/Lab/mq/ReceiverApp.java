package com.dengpf.Lab.mq;

/**
 * Created by kobe73er on 16/12/3.
 */

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReceiverApp {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("springJMSConfiguration.xml", "springJMSReceiver.xml");
    }
}