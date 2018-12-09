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

import com.xsjt.bean.Student;
import com.xsjt.dao.IStudentDao;
import com.xsjt.service.IStudentService;

/**  
 * ClassName:StudentServiceImpl 
 * Date:     2017年11月7日 上午9:59:13
 * @author   
 * @version    
 * @since    JDK 1.8
 */
@Service("studentService")
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private IStudentDao studentDao;
	
	@Override
	public List<Student> findStudents(Map<String, Object> map) {
		return studentDao.findStudents(map);
	}

	@Override
	public boolean addStudent(Student student) {
		return studentDao.addStudent(student);
	}
	
}