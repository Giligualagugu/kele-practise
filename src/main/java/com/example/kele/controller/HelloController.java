package com.example.kele.controller;

import com.example.kele.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

	@Autowired
	private HelloService helloService;

	@Autowired
	private ThreadPoolTaskExecutor asyncTaskExecutor;

	@GetMapping("/hello2")
	public String hello() {

		log.info("=====> asyncTaskExecutor is {}", asyncTaskExecutor);

		return "hello...";
	}

	@GetMapping("/test/ttc1")
	public String testcache(@RequestParam("k1") String k1, @RequestParam("k2") String k2) {

		return helloService.testCache(k1, k2);

	}

}
