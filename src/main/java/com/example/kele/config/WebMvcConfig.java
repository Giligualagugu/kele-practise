package com.example.kele.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.Duration;

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

	/**
	 * 设置下缓存的时间;
	 */
	@Bean
	public RedisCacheManager cacheManager(@Autowired RedisConnectionFactory redisConnectionFactory) {

		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofHours(2L))
				.disableCachingNullValues();

		RedisCacheManager build = RedisCacheManager.builder(redisConnectionFactory)
				.cacheDefaults(configuration)
				.transactionAware()
				.build();

		return build;

	}
}
