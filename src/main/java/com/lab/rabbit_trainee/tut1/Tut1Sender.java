package com.lab.rabbit_trainee.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

public class Tut1Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;


    @PostConstruct
    public void init(){
        System.out.println("Sender inited");
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(){
        String message = "Hello World";
        System.out.println(message);
        this.rabbitTemplate.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
