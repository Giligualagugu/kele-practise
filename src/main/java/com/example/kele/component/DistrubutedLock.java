package com.example.kele.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @AUTHOR xukele
 * @SINCE 2019/7/22 10:10
 */
@Slf4j
public class DistrubutedLock {

	private long expireTime = 5000L;

	private final String value = UUID.randomUUID().toString();

	private final String resourceKey;

	private StringRedisTemplate redisTemplate;

	public DistrubutedLock(String resourceKey, StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.resourceKey = resourceKey;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * 获取锁, 要锁住的资源自定义;
	 */
	public boolean getLock() {
		if (StringUtils.isEmpty(resourceKey)) {
			throw new IllegalArgumentException("resourceKey cannot be null or empty");
		}

		RedisCallback<Boolean> callback = connection -> {
			return connection.set(resourceKey.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8), Expiration.milliseconds(expireTime), RedisStringCommands.SetOption.SET_IF_ABSENT);
		};

		return redisTemplate.execute(callback);
	}

	/**
	 * 通过脚本解锁;
	 */
	public boolean releaseLock() {
		String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else return 0 end";

		RedisCallback<Boolean> callback = connection -> {
			return connection.eval(script.getBytes(StandardCharsets.UTF_8), ReturnType.BOOLEAN, 1, resourceKey.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8));
		};

		return redisTemplate.execute(callback);

	}
}
