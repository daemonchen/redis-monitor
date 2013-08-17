package com.redis.monitor;

import com.redis.monitor.redis.BasicRedisCacheServer;

public class RedisCacheThreadLocal {
	
	private  static ThreadLocal<BasicRedisCacheServer> redisCacheThreadLocal = new ThreadLocal<BasicRedisCacheServer>();
	
	public static void set(BasicRedisCacheServer basicRedisCacheServer) {
		redisCacheThreadLocal.set(basicRedisCacheServer);
	}
	
	public static BasicRedisCacheServer get() {
		return redisCacheThreadLocal.get();
	}
	
	public static void remove() {
		redisCacheThreadLocal.remove();
	}
	
	
 
}
