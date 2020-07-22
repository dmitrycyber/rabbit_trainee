package com.lab.rabbit_trainee.tut4;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

public class Tut4Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    AtomicInteger index = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    private final String[] keys = {"orange", "black", "green"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(){
        StringBuilder builder = new StringBuilder("Hello to ");

        if (this.index.incrementAndGet() == 3){
            this.index.set(0);
        }

        String key = keys[this.index.get()];

        builder.append(key).append(" ").append(this.count.incrementAndGet());

        String message = builder.toString();

        rabbitTemplate.convertAndSend(directExchange.getName(), key, message);

        System.out.println(" [x] Sent '" + message + "'");

    }

}
