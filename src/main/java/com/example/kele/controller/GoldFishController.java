package com.example.kele.controller;

import com.example.kele.component.DistrubutedLock;
import com.example.kele.entity.GoldFishEntity;
import com.example.kele.entity.GoldFishMessage;
import com.example.kele.repository.GoldFishRepository;
import com.example.kele.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RestController
public class GoldFishController {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private GoldFishRepository repository;

	@Autowired
	private HelloService helloService;
	private List<GoldFishMessage> messages = new LinkedList<>();

	@GetMapping("/test/add")
	public Object add() {
		GoldFishEntity build = GoldFishEntity.builder().joinDate(new Date())
				.nickName("xiaoming" + Math.random() * 1000)
				.captainMark("1")
				.openId("123123").build();

		repository.save(build);
		return build;
	}

	@GetMapping("/test/redislock")
	public void testredislock() {
		log.info("redisTemplate" + redisTemplate.getConnectionFactory().getConnection().getNativeConnection());
		DistrubutedLock lock = new DistrubutedLock("xiaoming222", redisTemplate);
		lock.setExpireTime(60 * 1000l);
		log.info("get lock ? " + lock.getLock());

		//	log.info("releas lock ? " + lock.releaseLock());

	}

}
