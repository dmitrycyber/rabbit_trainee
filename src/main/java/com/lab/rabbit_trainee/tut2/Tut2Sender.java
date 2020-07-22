package com.lab.rabbit_trainee.tut2;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

public class Tut2Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    private AtomicInteger dots = new AtomicInteger(0);

    private AtomicInteger count = new AtomicInteger(0);


    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(){
        StringBuilder builder = new StringBuilder("Hello");

        if (dots.incrementAndGet() == 4){
            dots.set(1);
        }

        for (int i = 0; i < dots.get(); i++) {
            builder.append(".");
        }

        builder.append(count.incrementAndGet());
        String message = builder.toString();

        this.rabbitTemplate.convertAndSend(queue.getName(), message);

        System.out.println(" [x] Sent '" + message + "'");
    }
}
