package com.jms.producer.demo.controller;

import com.jms.producer.demo.service.JmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageProducerController {

    @Autowired
    private JmsProducer jmsProducer;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        jmsProducer.sendMessage("test-queue", message);
        return "Message sent: " + message;
    }
}

