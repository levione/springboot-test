package com.xsjt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.xsjt.dynamicDataSource.DynamicDataSourceRegister;

@SpringBootApplication
@ServletComponentScan
// 注册动态多数据源
@Import({DynamicDataSourceRegister.class})
@MapperScan("com.xsjt.dao")
// 启用定时任务
@EnableScheduling
public class SpringbootTestApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootTestApplication.class, args);
	}
}