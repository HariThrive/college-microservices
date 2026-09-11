package com.college.department_college.service;

import java.util.List;

import com.college.department_college.entity.DepartmentCollege;
import com.college.department_college.vo.DepartmentCollegeVO;

public interface DepartmentCollegeService {

	String saveDepartment(DepartmentCollege department);

	List<DepartmentCollege> getAllDepartment();

	DepartmentCollege saveDepartmentCollege(DepartmentCollegeVO department);

	DepartmentCollege findByDepartmentId(Long departmentId);

	DepartmentCollegeVO getDepartmentWithCourses(Long departmentId);

}
