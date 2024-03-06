package com.khamilo.service_spring_rabbit_practice.Controller;

import com.khamilo.service_spring_rabbit_practice.Model.Message;
import com.khamilo.service_spring_rabbit_practice.Publisher.RabbitMQJsonProducer;
import com.khamilo.service_spring_rabbit_practice.Service.MessageMQ_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api_rabbit/v1")
public class MessageJSONController {

    @Autowired
    private RabbitMQJsonProducer jsonProducer;
    @Autowired
    private MessageMQ_Service messageMQ_service;

    public MessageJSONController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> post_JsonMessage(@RequestBody Message message)throws Exception{

        try {

            Message message_up = messageMQ_service.post_guardarMessage(message);

            jsonProducer.sendJsonMessage(message);

            return ResponseEntity.ok("Mensaje JSON enviado a RabbitMQ ..."+message_up);


        }catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/publish/{id_message}")
    public ResponseEntity<?> get_listarMSByID( @PathVariable("id_message") Long id_message )throws Exception{

        try{
            return ResponseEntity.ok( messageMQ_service.get_mostrarMessageByID(id_message) );
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/publish/")
    public ResponseEntity<?> get_listarMessages() throws Exception {

        try{
            return ResponseEntity.ok(messageMQ_service.get_mostrarMessages());
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
