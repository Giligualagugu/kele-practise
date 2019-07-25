package com.example.kele.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

/**
 * @AUTHOR xukele
 * @SINCE 2019/7/23 15:17
 */
@Configuration
public class CacheConfig {

	/**
	 * 设置下redis缓存的时间;
	 */
	@Bean
	public RedisCacheManager cacheManager(@Autowired RedisConnectionFactory redisConnectionFactory) {

		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofHours(1L))
				.disableCachingNullValues();

		return RedisCacheManager.builder(redisConnectionFactory)
				.cacheDefaults(configuration)
				.transactionAware()
				.build();

	}

}
