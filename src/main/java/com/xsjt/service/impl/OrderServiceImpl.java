/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2017, xsjt- All Rights Reserved.
 * Project Name:springboot-test  
 * File Name:StudentServiceImpl.java  
 * Package Name:com.xsjt.service
 * 
 * Date:2017年11月7日上午9:59:13
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsjt.bean.Order;
import com.xsjt.dao.IOrderDao;
import com.xsjt.dynamicDataSource.TargetDataSource;
import com.xsjt.service.IOrderService;

/**  
 * ClassName:OrderServiceImpl
 * Date:     2017年11月7日 上午9:59:13
 * @author   
 * @version    
 * @since    JDK 1.8
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private IOrderDao orderDao;

	// 使用数据源ds1
	@TargetDataSource(name="ds1")
	@Override
	public List<Order> findOrders(Map<String, Object> map) {
		return orderDao.findOrders(map);
	}

	// 使用数据源ds1
	@Transactional
	@TargetDataSource(name="ds1")
	@Override
	public boolean addOrder(Order order) {
		return orderDao.addOrder(order);
	}
}