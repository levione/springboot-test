/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2017, xsjt- All Rights Reserved.
 * Project Name:springboot-test  
 * File Name:SpringInterceptorRegister.java  
 * Package Name:com.xsjt.config
 * 
 * Date:2017年11月6日下午4:58:32
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.xsjt.interceptor.JoeInterceptor;
import com.xsjt.interceptor.XbqInterceptor;

/**  
 * ClassName:SpringInterceptorRegister 
 * Spring 拦截器 注册器
 * Date:     2017年11月6日 下午4:58:32
 * @author   
 * @version    
 * @since    JDK 1.8
 */
@SuppressWarnings("deprecation")
@Configuration
public class SpringInterceptorRegister extends WebMvcConfigurationSupport {

	/**
	 * TODO 添加spring中的拦截器.  
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 拦截所有路径
		registry.addInterceptor(new XbqInterceptor()).addPathPatterns("/**");
		// 拦截/freemarker后路径
		registry.addInterceptor(new JoeInterceptor()).addPathPatterns("/freemarker/**");
		super.addInterceptors(registry);
	}

	/**
	 * TODO  注册静态文件的自定义映射路径  
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("classpath:/image/");
        registry.addResourceHandler("/picture/**").addResourceLocations("file:D:/picture/");
        super.addResourceHandlers(registry);
    }
}