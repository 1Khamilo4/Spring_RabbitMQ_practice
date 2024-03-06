package com.khamilo.service_spring_rabbit_practice.Repository;


import com.khamilo.service_spring_rabbit_practice.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMQ_Repository extends JpaRepository<Message, Long> {
}
