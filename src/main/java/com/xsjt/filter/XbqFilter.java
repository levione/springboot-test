/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2017, xsjt- All Rights Reserved.
 * Project Name:springboot-test  
 * File Name:JoeFilter.java  
 * Package Name:com.xsjt.filter
 * 
 * Date:2017年11月6日下午6:01:33
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * ClassName:JoeFilter 
 * 自定义 Serlvlet 的过滤器 
 * Date:     2017年11月6日 下午6:01:33
 * @author   
 * @version    
 * @since    JDK 1.8
 */
public class XbqFilter implements Filter {
	
	private static Logger logger = LoggerFactory.getLogger(XbqFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("--xbq--初始化JoeFilter!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		logger.info(req.getRequestURL() + "---xbq---> doFilter ");
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		logger.info("--xbq--销毁JoeFilter!");
	}

}
  
