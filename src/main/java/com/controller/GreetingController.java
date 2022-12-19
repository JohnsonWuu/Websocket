package com.controller;

import com.data.Greeting;
import com.data.HelloMessage;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
//
//        System.out.println("HelloMessage name: " + message.getName());
//
//        Thread.sleep(1000); // simulated delay
//
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }

    @MessageMapping("/hello")
    public void greeting2(HelloMessage message) throws Exception {

        System.out.println("HelloMessage name: " + message.getName());

        Thread.sleep(1000); // simulated delay

        JSONObject obj = new JSONObject();
        obj.put("Name",message.getName());
                simpMessagingTemplate.convertAndSend("/topic/greetings",  obj.toJSONString());
    }

}
