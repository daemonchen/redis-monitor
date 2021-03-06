package com.redis.monitor;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redis.monitor.redis.BasicRedisCacheServer;


public class RedisConfigInit {
	
	public static final Logger logger = LoggerFactory.getLogger(RedisConfigInit.class);
	
	public static final String DEFAULT_PATH = "";
	public static final String DEFAULT_FILE_NAME = "Redis-Server-Config.xml";
	
	public static String FILE_PATH = "";
	public static String FILE_NAME = DEFAULT_FILE_NAME;
	
	private List<RedisServer> listRS;
	
	static {
		if (!Constants.REDIS_SERVER_CONFIG_PATH.equals(""))
			FILE_PATH = Constants.REDIS_SERVER_CONFIG_PATH;
		
		if (!Constants.REDIS_SERVER_CONFIG_FILE_NAME.equals(""))
			FILE_NAME = Constants.REDIS_SERVER_CONFIG_FILE_NAME;
	}
	
	public void init() {
		resolveXml();
	}

	/**
	 * 解析RedisServerConfig配置
	 */
	private void resolveXml() {
		SAXReader saxReader = new SAXReader();
		try {
			File file = new File(FILE_PATH + FILE_NAME);
			if (!file.exists()) {
				logger.info("file [{}] not found , server init default config ",FILE_PATH + FILE_NAME);
			}
			InputStream input = Thread.currentThread().getContextClassLoader().getSystemResourceAsStream(DEFAULT_PATH + DEFAULT_FILE_NAME);
			Document document = saxReader.read(input);
			Element rootElement = document.getRootElement();
			
			Element rsEL = rootElement.element("Redis-Servers");
			listRS = new ArrayList<RedisServer>();
			for (Iterator<Element> itr = rsEL.elementIterator(); itr.hasNext();){
				Element s = itr.next();
				RedisServer rs = convertElementToRs(s);
				
				List<Element> slaves = s.element("Slaves").elements();
				
				List<RedisServer> slaveList = null;
				if (slaves != null && slaves.size() > 0) slaveList = new ArrayList<RedisServer>();
				
				for (Element slave : slaves) {
					RedisServer slaveRs = convertElementToRs(slave);
					slaveRs.setMasterRedisServer(rs);
					slaveList.add(convertElementToRs(slave));
				}
				
				rs.setSlaveRedisServer(slaveList);
				listRS.add(rs);
			}
			RedisJedisPool.initRedisJedisPool(listRS);
			logger.info("redis manager build success!");
		} catch (DocumentException e) {
			logger.error("redis manager build error!");
			e.printStackTrace();
		}
	}
	
	private RedisServer convertElementToRs(Element el) {
		String uuid = el.attribute("uuid").getValue();
		String name = el.element("name").getText();
		String description = el.element("description").getText();
		String host = el.element("host").getText();
		int port = Integer.parseInt(el.element("port").getText());
		int maxActive = Integer.parseInt(el.element("maxActive").getText());
		int maxIdle = Integer.parseInt(el.element("maxIdle").getText());
		int maxWait = Integer.parseInt(el.element("maxWait").getText());
		boolean testOnBorrow = Boolean.parseBoolean(el.element("testOnBorrow").getText());
		boolean isMaster = Boolean.parseBoolean(el.element("isMaster").getText());
		
		RedisServer rs = new RedisServer();
		rs.setUuid(uuid);
		rs.setName(name);
		rs.setDescription(description);
		rs.setHost(host);
		rs.setPort(port);
		rs.setMaxActive(maxActive);
		rs.setMaxIdle(maxIdle);
		rs.setMaxWait(maxWait);
		rs.setTestOnBorrow(testOnBorrow);
		rs.setMaster(isMaster);
		return rs;
	}
	
	public static void main(String[] args) {
		new RedisConfigInit().resolveXml();
		BasicRedisCacheServer brc =RedisJedisPool.getRedisCacheServer("0102");
		for (Object o : brc.slowLog()) {
			List<Object> obj = (List<Object>)o;
			System.out.println(obj.get(0));
		}
	}

}
