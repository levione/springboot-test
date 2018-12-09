/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2017, xsjt- All Rights Reserved.
 * Project Name:spring-boot-learn  
 * File Name:TargetDataSource.java  
 * Package Name:com.xsjt.dynamicDataSource
 * 
 * Date:2017年11月13日下午7:42:15
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.dynamicDataSource;  

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**  
 * ClassName:TargetDataSource 
 * Date:     2017年11月13日 下午7:42:15
 * @author   
 * @version    
 * @since    JDK 1.8
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
