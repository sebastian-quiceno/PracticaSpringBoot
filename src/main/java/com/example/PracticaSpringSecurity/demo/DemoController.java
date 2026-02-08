package com.example.PracticaSpringSecurity.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demoController")
public class DemoController {
    @GetMapping
    public ResponseEntity<String> satyHello(){
        return ResponseEntity.ok("Hola desde un endpoint seguro");
    }
}
