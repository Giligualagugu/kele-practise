package com.example.kele;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableCaching
@EnableEurekaClient
@SpringBootApplication
public class KelePractiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(KelePractiseApplication.class, args);
	}

}
