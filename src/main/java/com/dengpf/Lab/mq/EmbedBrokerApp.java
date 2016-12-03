package com.dengpf.Lab.mq;

/**
 * Created by kobe73er on 16/12/3.
 */

import java.io.IOException;

import static com.dengpf.Lab.mq.SenderApp.start;


public class EmbedBrokerApp {
    public static void main(String[] args) throws IOException {
        start("embedBroker.xml");
    }
}