package com.lab.rabbit_trainee.tut3;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;


public class Tut3Receiver {

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String message) throws InterruptedException {
        receive(message, 1);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String message) throws InterruptedException{
        receive(message, 2);
    }


    @RabbitHandler
    public void receive(String message, int receiver) throws InterruptedException{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("instance " + receiver + " [x] Received'" + message + "'");
        doWork(message);
        stopWatch.stop();

        System.out.println("instance " + receiver + " [x] Done in " + stopWatch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String message) throws InterruptedException {
        for (char ch : message.toCharArray()){
            if (ch == '.'){
                Thread.sleep(1000);
            }
        }
    }

}
