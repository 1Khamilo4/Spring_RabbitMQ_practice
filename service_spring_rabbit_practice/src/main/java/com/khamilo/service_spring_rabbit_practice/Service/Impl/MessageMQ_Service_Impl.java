package com.khamilo.service_spring_rabbit_practice.Service.Impl;

import com.khamilo.service_spring_rabbit_practice.Model.Message;
import com.khamilo.service_spring_rabbit_practice.Repository.MessageMQ_Repository;
import com.khamilo.service_spring_rabbit_practice.Service.MessageMQ_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageMQ_Service_Impl implements MessageMQ_Service {

    @Autowired
    private MessageMQ_Repository messageMQRepository;
    @Override
    public Message post_guardarMessage(Message message) throws Exception {

        try{

            if(message.getMessage().equals("")  ){
                throw new Exception("Error al guardar el mensaje");
            }

            return messageMQRepository.save(message);

        }catch (Exception e){

            throw new Exception("Error al guardar el mensaje");

        }
    }

    @Override
    public List<Message> get_mostrarMessages() throws Exception {
        try{
            List<Message> messages = messageMQRepository.findAll();


            if(!messages.isEmpty()){
                return messages;
            }

            throw new Exception("No hay mensajes guardados ");

        }catch (Exception e){
            throw new Exception("No hay mensajes guardados ");
        }
    }

    @Override
    public Message get_mostrarMessageByID(Long id_message) throws Exception {
        Optional<Message> message = messageMQRepository.findById(id_message);

        if( !message.isPresent() ){
            throw new Exception("No se encontro el mensaje con el id "+ id_message);

        }
        return message.get();
    }
}
