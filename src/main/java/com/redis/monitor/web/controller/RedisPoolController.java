package com.redis.monitor.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.redis.monitor.RedisCacheThreadLocal;
import com.redis.monitor.RedisJedisPool;

@Controller
@RequestMapping("/redis/pool")
public class RedisPoolController extends BaseProfileController {
	
	@RequestMapping(value="/list.htm",method=RequestMethod.POST)
	public void redisPoolList() {
		
	}
	
	@RequestMapping(value="/${uuid}/checked.htm",method=RequestMethod.GET)
	public void checkedPool(@PathVariable String uuid) {
		//TODO 根据uuid切换到对应的redisManager上
		RedisCacheThreadLocal.set(RedisJedisPool.getRedisCacheServer(uuid));
	}

}
