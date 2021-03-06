/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2017, xsjt- All Rights Reserved.
 * Project Name:springboot-test  
 * File Name:XbqInterceptor.java  
 * Package Name:com.xsjt.interceptor
 * 
 * Date:2017年11月6日下午4:56:27
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**  
 * ClassName:XbqInterceptor 
 * 自定义拦截器
 * Date:     2017年11月6日 下午4:56:27
 * @author   
 * @version    
 * @since    JDK 1.8
 */
public class JoeInterceptor implements HandlerInterceptor{
	
	private static final Logger logger = LoggerFactory.getLogger(JoeInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info(this.getClass().getName() + "<---joe--->preHandle");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.info(this.getClass().getName() + "<---joe--->postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		logger.info(this.getClass().getName() + "<---joe--->afterCompletion");
	}
}