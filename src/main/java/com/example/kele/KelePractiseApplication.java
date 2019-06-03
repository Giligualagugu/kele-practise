package com.example.kele;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
//@EnableEurekaClient
@SpringBootApplication
public class KelePractiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(KelePractiseApplication.class, args);
	}

}
