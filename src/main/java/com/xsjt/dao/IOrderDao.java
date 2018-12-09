/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2017, xsjt- All Rights Reserved.
 * Project Name:springboot-test  
 * File Name:IStudentDao.java  
 * Package Name:com.xsjt.dao
 * 
 * Date:2017年11月7日上午9:22:59
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.dao;

import java.util.List;
import java.util.Map;

import com.xsjt.bean.Order;

/**  
 * ClassName:IStudentDao 
 * Date:     2017年11月7日 上午9:22:59
 * @author   
 * @version    
 * @since    JDK 1.8
 */
public interface IOrderDao {

	/**
	 * findOrders:(查询List).  
	 * @author 
	 * Date:2017年11月7日上午9:30:37
	 *
	 * @param map
	 * @return
	 */
	List<Order> findOrders(Map<String, Object> map);
	
	/**
	 * addOrder:(增加).  
	 * @author 
	 * Date:2017年11月7日上午9:30:48
	 *
	 * @param order
	 * @return
	 */
	boolean addOrder(Order order);
	
}
  
