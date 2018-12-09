/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2017, xsjt- All Rights Reserved.
 * Project Name:spring-boot-learn  
 * File Name:TestRedis.java  
 * Package Name:com.xsjt.redis
 * 
 * Date:2017年11月14日下午8:09:54
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.redis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**  
 * ClassName:TestRedis 
 * Date:     2017年11月14日 下午8:09:54
 * @author   
 * @version    
 * @since    JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
	
	/********************************测试String***********************************/
	
	@Autowired
	private StringRedisUtil stringRedis;
	
	@Test
	public void setString() {
		stringRedis.set("name", "张三");
	}
	
	@Test
	public void getString() {
		Object value = stringRedis.get("name");
		System.out.println("value=" + value);
	}
	
	/**********************************测试Hash************************************/
	
	@Autowired
	private HashRedisUtil<Object> hashRedisUtil;
	
	@Test
	public void setHash() {
		hashRedisUtil.put("user", "userName",  new Integer(6868), 5);
	}
	
	@Test
	public void getHash() {
		Integer a = (Integer) hashRedisUtil.get("user", "userName");
		System.out.println("a==" + a);
	}
}
  
