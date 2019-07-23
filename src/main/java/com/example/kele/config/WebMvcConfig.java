package com.example.kele.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Override
	protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
		poolTaskExecutor.setCorePoolSize(10);
		poolTaskExecutor.setMaxPoolSize(100);
		poolTaskExecutor.setQueueCapacity(300);
		poolTaskExecutor.setBeanName("kele-taskExecutor");
		poolTaskExecutor.setThreadNamePrefix("kele_th_");
		poolTaskExecutor.initialize();
		configurer.setTaskExecutor(poolTaskExecutor);

	}

	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
	}

}
