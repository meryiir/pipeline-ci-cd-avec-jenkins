package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Bienvenue dans l'application Spring Boot Demo !";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot CI/CD Pipeline !";
    }

    @GetMapping("/health")
    public String health() {
        return "Application is running !";
    }
}
