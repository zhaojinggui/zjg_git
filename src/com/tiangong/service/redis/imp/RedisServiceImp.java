package com.tiangong.service.redis.imp;

import com.tiangong.service.redis.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisServiceImp implements RedisService {

	public StringRedisTemplate redisTemplate;
	
	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void addString(String key, String value) {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public String getValue(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public void delString(String key) {
		// TODO Auto-generated method stub
		redisTemplate.delete(key);
	}
}
