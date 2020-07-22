package com.lab.rabbit_trainee.tut5;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

public class Tut5Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    AtomicInteger index = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    private final String[] keys = {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(){
        StringBuilder builder = new StringBuilder("Hello to ");

        if (this.index.incrementAndGet() == keys.length){
            this.index.set(0);
        }

        String key = keys[this.index.get()];

        builder.append(key).append(" ").append(this.count.incrementAndGet());

        String message = builder.toString();

        rabbitTemplate.convertAndSend(topicExchange.getName(), key, message);

        System.out.println(" [x] Sent '" + message + "'");

    }
}
