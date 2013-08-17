package com.redis.monitor.manager.impl;

import java.util.List;
import java.util.Map;

import com.redis.monitor.RedisCacheThreadLocal;
import com.redis.monitor.RedisConfigXml;
import com.redis.monitor.RedisInfoDetail;
import com.redis.monitor.RedisServer;
import com.redis.monitor.manager.RedisManager;
import com.redis.monitor.redis.BasicRedisCacheServer;

public class RedisManagerImpl implements RedisManager {
	
	private BasicRedisCacheServer basicRedisCacheServer;
	
	
	public RedisManagerImpl () {
		basicRedisCacheServer = RedisCacheThreadLocal.get();
	}

	public List<RedisServer> redisServerList() {
		return null;
	}

	public RedisInfoDetail getRedisInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public RedisConfigXml getRedisConfigXmlDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> getRedisConfigByPattern(String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	public String configRedisConfigXml(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getRedisDbSize() {
		// TODO Auto-generated method stub
		return null;
	}

	public String ping() {
		// TODO Auto-generated method stub
		return null;
	}

	public String flushAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public String flushDb() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
