package com.lab.rabbit_trainee.tut1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import javax.annotation.PostConstruct;


@RabbitListener(queues = "hello")
public class Tut1Receiver {

    @PostConstruct
    public void init(){
        System.out.println("Receiver inited");
    }


    @RabbitHandler
    public void receive(String message){
        System.out.println(" [x] Received'" + message + "'");
    }

}
