package com.dengpf.Lab.mq;

/**
 * Created by kobe73er on 16/12/3.
 */

import org.springframework.jms.core.JmsTemplate;

public class MessageSender {

    private final JmsTemplate jmsTemplate;

    public MessageSender(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(final String text) {
        jmsTemplate.convertAndSend(text);
    }
}



