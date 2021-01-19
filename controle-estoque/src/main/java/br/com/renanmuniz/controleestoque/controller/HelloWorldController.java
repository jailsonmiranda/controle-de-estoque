package br.com.renanmuniz.controleestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class HelloWorldController {

    @RequestMapping("/helloworld")
    @ResponseBody
    public String hello() {
        System.out.println("Hello World Spring Boot " + LocalDateTime.now());
        return "Hello World Spring Boot " + LocalDateTime.now();
    }
}
