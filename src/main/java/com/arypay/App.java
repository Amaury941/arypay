package com.arypay;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class App {
    int test = 0;
    public static void main (String[] argv) {
        SpringApplication.run(App.class,argv);
    }
    @GetMapping("/")
    public String hello(@RequestParam(value="name",defaultValue = "world") String name) {
        return String.format("Hello %s", name);
    }
}