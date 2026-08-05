package com.college.student.client;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.college.student.entity.DepartmentCollege;

@HttpExchange
public interface DepartmentCollegeClient {
	@GetExchange("/department/student/{departmentId}")
	DepartmentCollege findByDepartmentId(@PathVariable ("departmentId") Long departmentId);
	
	@GetExchange("/department/get")
	List<DepartmentCollege> getAllDepartment();
}
