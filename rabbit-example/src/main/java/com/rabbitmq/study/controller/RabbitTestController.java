package com.rabbitmq.study.controller;
import com.rabbitmq.study.produce.HelloSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rabbit")
public class RabbitTestController {

    @Autowired
    private HelloSender helloSender;

    @GetMapping("/hello")
    public void hello() {
        helloSender.send();
    }
}
