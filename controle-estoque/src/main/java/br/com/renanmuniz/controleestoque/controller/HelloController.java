package br.com.renanmuniz.controleestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/helloworld")
    @ResponseBody
    public String hello() {
        System.out.println("Hello World Spring Boot");
        return "Hello World Spring Boot";
    }
}
