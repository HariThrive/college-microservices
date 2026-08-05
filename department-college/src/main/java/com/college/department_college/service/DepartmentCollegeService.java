package com.college.department_college.service;

import java.util.List;

import com.college.department_college.entity.DepartmentCollege;

public interface DepartmentCollegeService {

	String saveDepartment(DepartmentCollege department);

	List<DepartmentCollege> getAllDepartment();

	DepartmentCollege saveDepartmentCollege(DepartmentCollege department);

	DepartmentCollege findByDepartmentId(Long departmentId);

}
