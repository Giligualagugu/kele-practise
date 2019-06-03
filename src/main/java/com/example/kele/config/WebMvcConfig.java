package com.example.kele.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {


    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {

        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(10);

        poolTaskExecutor.setMaxPoolSize(100);

        poolTaskExecutor.setQueueCapacity(300);

        poolTaskExecutor.initialize();

        poolTaskExecutor.setBeanName("kele-taskExecutor");

        poolTaskExecutor.setThreadNamePrefix("kele_th_");

        configurer.setTaskExecutor(poolTaskExecutor);

    }
}
