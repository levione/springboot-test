/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2017, xsjt- All Rights Reserved.
 * Project Name:springboot-test  
 * File Name:JoeListener.java  
 * Package Name:com.xsjt.listener
 * 
 * Date:2017年11月6日下午6:28:29
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * ClassName:JoeListener 
 * Date:     2017年11月6日 下午6:28:29
 * @author   
 * @version    
 * @since    JDK 1.8
 */
@WebListener
public class JoeListener implements ServletContextListener{
	
	private static Logger logger = LoggerFactory.getLogger(JoeListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("--Joe-监听器-ServletContext 初始化");
		logger.info(sce.getServletContext().getServerInfo());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("--Joe-监听器-ServletContext 销毁");
	}
}
  
