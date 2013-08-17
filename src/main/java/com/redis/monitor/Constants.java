package com.redis.monitor;

import java.util.ResourceBundle;

public class Constants {
	
	public static final ResourceBundle config = ResourceBundle.getBundle("config");
	
	public static final String JSON_VIEW = "jsonView";
	
	public static final String REDIS_SERVER_CONFIG_PATH = config.getString("redisServerConfigPath");
	public static final String REDIS_SERVER_CONFIG_FILE_NAME = config.getString("redisServerConfigName");
	

}
