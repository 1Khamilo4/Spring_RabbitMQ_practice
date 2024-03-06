package com.khamilo.service_spring_rabbit_practice.Consumer;


import com.khamilo.service_spring_rabbit_practice.Model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJSONConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJSONConsumer.class);

    @RabbitListener( queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJSonMessage( Message message ){

        LOGGER.info(String.format("Consumer: Mensaje JSON Recibido -> %s", message.toString()));

        makeSlow();
    }

    private void makeSlow(){

        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
