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

import com.xsjt.bean.OrderDetail;
import com.xsjt.dao.IOrderDetailDao;
import com.xsjt.dynamicDataSource.TargetDataSource;
import com.xsjt.service.IOrderDetailService;

/**  
 * ClassName:OrderServiceImpl
 * Date:     2017年11月7日 上午9:59:13
 * @author   
 * @version    
 * @since    JDK 1.8
 */
@Service("orderDetailService")
public class OrderDetailServiceImpl implements IOrderDetailService{
	
	@Autowired
	private IOrderDetailDao orderDetailDao;

	// 使用数据源ds2
	@TargetDataSource(name="ds2")
	@Override
	public List<OrderDetail> findOrderDetail(Map<String, Object> map) {
		return orderDetailDao.findOrderDetail(map);
	}

	// 使用数据源ds2
	@Transactional
	@TargetDataSource(name="ds2")
	@Override
	public boolean addOrderDeatil(OrderDetail detail) {
		return orderDetailDao.addOrderDetail(detail);
	}

}