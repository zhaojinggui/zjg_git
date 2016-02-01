package com.tiangong.service.redis;

public interface RedisService {
	
	public void addString(String key ,String value);
	
	public String getValue(String key);
	
	public void delString(String key);

}
