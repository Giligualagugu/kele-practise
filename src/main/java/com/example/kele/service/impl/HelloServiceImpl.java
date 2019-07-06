package com.example.kele.service.impl;

import com.example.kele.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

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
