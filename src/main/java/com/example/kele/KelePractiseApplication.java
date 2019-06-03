package com.example.kele;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class KelePractiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(KelePractiseApplication.class, args);
	}

}
