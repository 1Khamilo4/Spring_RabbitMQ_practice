package com.khamilo.service_spring_rabbit_practice.Publisher;

import com.khamilo.service_spring_rabbit_practice.Model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchage;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    public static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(Message message){

        LOGGER.info( String.format("Producer: Mensaje JSON enviado -> %s", message) );

        this.rabbitTemplate.convertAndSend( this.exchage, this.routingJsonKey, message );

    }
}
