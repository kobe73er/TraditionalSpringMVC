package com.dengpf.Lab.mq;

/**
 * Created by kobe73er on 16/12/3.
 */

import javax.jms.*;

public class MessageReceiver implements MessageListener {

    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String text = textMessage.getText();
                System.out.println(String.format("Received: %s", text));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}