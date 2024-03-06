package com.khamilo.service_spring_rabbit_practice.Config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //Mensaje normal
    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    //Mensaje JSON

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    @Bean
    public Queue queue(){
        return new Queue( this.queue );
    }

    @Bean
    public Queue jsonQueue(){
        return new Queue(this.jsonQueue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange( this.exchange );
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind( this.queue() )
                .to( this.exchange() )
                .with( this.routingKey );
    }

    @Bean
    public Binding jsonBindig(){
        return BindingBuilder.bind( this.jsonQueue() )
                .to( this.exchange() )
                .with(this.routingJsonKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());

        return rabbitTemplate;

    }
}
