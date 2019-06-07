package com.example.kele.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {


    @Autowired
    private AsyncTaskExecutor asyncTaskExecutor;

    @GetMapping("/hello2")
    public String hello() {

        log.info("=====> asyncTaskExecutor is {}", asyncTaskExecutor);

        return "hello...";
    }
}
