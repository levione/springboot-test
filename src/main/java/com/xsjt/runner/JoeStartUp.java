/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2017, xsjt- All Rights Reserved.
 * Project Name:springboot-test  
 * File Name:XbqStartUp.java  
 * Package Name:com.xsjt.runner
 * 
 * Date:2017年11月6日下午7:46:48
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**  
 * ClassName:XbqStartUp 
 * 启动加载数据
 * Date:     2017年11月6日 下午7:46:48
 * @author   
 * @version    
 * @since    JDK 1.8
 */
@Component
@Order(value = 2)
public class JoeStartUp implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(JoeStartUp.class);

	@Override
	public void run(String... args) throws Exception {
		logger.info(this.getClass().getName() + "启动加载数据********" + args);
	}
}
  
