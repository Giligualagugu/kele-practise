package com.example.kele.service.impl;

import com.example.kele.entity.GoldFishEntity;
import com.example.kele.repository.GoldFishRepository;
import com.example.kele.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

	@Autowired
	private GoldFishRepository goldFishRepository;

	/**
	 * 两种 包的 注解都会使事务生效;
	 */
	@Transactional
	@Override
	public String testTR(String k1) {

		GoldFishEntity entity = GoldFishEntity.builder().nickName("小米222").joinDate(new Date()).openId("12sajdga").build();
		goldFishRepository.save(entity);
		throw new RuntimeException("12");
//		/return null;
	}

	/**
	 * 如果使用缓存,入参不能为null;
	 *
	 * @param k1
	 * @param k2
	 * @return
	 */
	@Override
	@Cacheable(value = "HelloService", key = "targetClass + methodName +#p0 +#p1")
	public String testCache(String k1, String k2) {

		return "666" + k1 + k2;
	}
}
