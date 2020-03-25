package com.ule.demo.schedule.config;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 项目需要用到的配置参数类
 * @author fanxl
 *
 */
@Slf4j
public class Config {
	private static ConcurrentHashMap<String,String> storeMap=new ConcurrentHashMap();
	static{
		Properties config = new Properties();
		InputStream in = new Config().getClass().getClassLoader().getResourceAsStream("task.properties");
		try {
			config.load(in);
			Enumeration<?> e = config.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = config.getProperty(key);
				storeMap.put(key,value);
			}
		} catch (Exception e) {
			log.error("======Config======",e);
		}
	}

	public static String getValueByKey(String key){
		return storeMap.get(key);
	}
	public static void main(String[] args) {
		System.out.println(Config.getValueByKey("serviceUrl"));
	}
	
}
