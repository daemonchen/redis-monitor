package com.redis.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.redis.monitor.RedisServer;
import com.redis.monitor.redis.BasicRedisCacheServer;
import com.redis.monitor.redis.RedisCacheServer;


public class RedisJedisPool {
	
	private static Map<String,BasicRedisCacheServer> map = new HashMap<String, BasicRedisCacheServer>();;
	private static List<RedisServer> list = new ArrayList<RedisServer>();
	
	public RedisJedisPool () {}

	public static void initRedisJedisPool(List<RedisServer> rsList) {
		for (RedisServer rs : rsList) {
			list = rsList;
			BasicRedisCacheServer server = new RedisCacheServer(rs.getHost(), rs.getPort(), rs.getMaxActive(), rs.getMaxIdle(), rs.getMaxWait(), rs.isTestOnBorrow());
			map.put(rs.getUuid(), server);
			
			List<RedisServer> slaveRs = rs.getSlaveRedisServer();
			for (RedisServer slRs : slaveRs) {
				BasicRedisCacheServer readServer = new RedisCacheServer(slRs.getHost(), slRs.getPort(), slRs.getMaxActive(), slRs.getMaxIdle(), slRs.getMaxWait(), slRs.isTestOnBorrow());
				map.put(slRs.getUuid(), readServer);
			}
		}
	}
	
	public static BasicRedisCacheServer getRedisCacheServer(String uuid) {
		return map.get(uuid);
	}


	public static Map<String, BasicRedisCacheServer> getRedisCacheMap() {
		return map;
	}
	
	
	
}
