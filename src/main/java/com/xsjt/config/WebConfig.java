/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2017, xsjt- All Rights Reserved.
 * Project Name:springboot-test  
 * File Name:WebConfig.java  
 * Package Name:com.xsjt.config
 * 
 * Date:2017年11月6日下午6:10:30
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.config;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.xsjt.filter.XbqFilter;
import com.xsjt.listener.XbqListener;
import com.xsjt.servlet.XbqServlet;

/**  
 * ClassName:WebConfig 
 * 通过 @bean 注入 servlet、filter、listener
 * Date:     2017年11月6日 下午6:10:30
 * @author   
 * @version    
 * @since    JDK 1.8
 */
//@Configuration
public class WebConfig {

	/**
	 * servletRegistrationBean:(使用代码注册Servlet（不需要@ServletComponentScan注解）). 
	 * @author 
	 * Date:2017年11月6日下午6:13:29
	 *
	 * @return
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean(){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean();
		registrationBean.setServlet(new XbqServlet());
		List<String> urlMappings = new ArrayList<String>();
		// 访问，可以添加多个
		urlMappings.add("/xbq/servlet");
		registrationBean.setUrlMappings(urlMappings);
		registrationBean.setLoadOnStartup(1);
		return registrationBean;
	}
	
	/**
	 * getDemoFilter:(使用代码注册拦截器).  
	 * @author 
	 * Date:2017年11月6日下午6:17:31
	 *
	 * @return
	 */
	@Bean
	public FilterRegistrationBean getDemoFilter(){
		XbqFilter demoFilter = new XbqFilter();
		FilterRegistrationBean registrationBean=new FilterRegistrationBean();
		registrationBean.setFilter(demoFilter);
		List<String> urlPatterns=new ArrayList<String>();
		urlPatterns.add("/*");							//拦截路径，可以添加多个
		registrationBean.setUrlPatterns(urlPatterns);
		registrationBean.setOrder(1);
		return registrationBean;
	}
	
	/**
	 * getDemoListener:(使用代码 引用 监听器).  
	 * @author 
	 * Date:2017年11月6日下午6:35:28
	 *
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<EventListener> getDemoListener(){
		ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<>();
		registrationBean.setListener(new XbqListener());
		registrationBean.setOrder(1);
		return registrationBean;
	}
}