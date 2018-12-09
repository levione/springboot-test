/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2017, xsjt- All Rights Reserved.
 * Project Name:spring-boot-learn  
 * File Name:RedisUtil.java  
 * Package Name:com.xsjt.redis
 * 
 * Date:2017年11月14日下午7:46:44
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.redis;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
/**
 * ClassName: StringRedisUtil
 * String 数据类型
 * date: 2017年11月14日 下午8:15:07
 * @author   
 * @version   
 * @since JDK 1.8
 */
@Component("stringRedis")
public class StringRedisUtil {

	@Autowired
	private ValueOperations<String, Object> redisTemplate;
	
	/**
	 * set:(保存数据).  
	 * @author 
	 * Date:2017年11月14日下午8:15:01
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, String value){
		redisTemplate.set(key, value);
	}
	
	/**
	 * get:(得到数据).  
	 * @author 
	 * Date:2017年11月14日下午8:15:38
	 *
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return redisTemplate.get(key);
	}
	
	// 可自行扩展其他方法
}