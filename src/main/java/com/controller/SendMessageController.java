package com.controller;

import com.data.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/put_message")
    public String putMessage(@RequestParam(name="name") String name, @RequestParam(name="age") Integer age) {
        rabbitTemplate.convertAndSend("tpu.queue", new User(name,age));
        return "this is quick demo for Spring Boot!";
    }

}