package com.khamilo.service_spring_rabbit_practice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name="mensajes_mq")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id_message;

    private String message;
}
