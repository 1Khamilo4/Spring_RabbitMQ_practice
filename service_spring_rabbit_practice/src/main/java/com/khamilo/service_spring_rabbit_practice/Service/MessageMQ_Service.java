package com.khamilo.service_spring_rabbit_practice.Service;

import com.khamilo.service_spring_rabbit_practice.Model.Message;

import java.util.List;

public interface MessageMQ_Service {

    public Message post_guardarMessage( Message message ) throws Exception;

    public List<Message> get_mostrarMessages() throws Exception;

    public  Message get_mostrarMessageByID( Long id_message ) throws Exception;


}
