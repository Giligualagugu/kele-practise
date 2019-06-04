package com.example.kele.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xukele
 * @TIME 2019/6/4 13:41
 * kele-practise & com.example.kele.controller
 */
@RestController
@Slf4j
public class RedisTestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private LettuceConnectionFactory lettuceConnectionFactory;

    @GetMapping("/ttredis")
    public Object testRedis() {

        stringRedisTemplate.opsForValue().set("tom", "malisu", 60 * 1000L);

        String tom = stringRedisTemplate.opsForValue().get("tom");

        log.info("==>{}", lettuceConnectionFactory);

        return tom;

    }

}
