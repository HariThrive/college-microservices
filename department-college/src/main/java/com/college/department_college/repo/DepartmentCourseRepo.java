package com.college.department_college.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.department_college.entity.DepartmentCourse;

import java.util.List;

public interface DepartmentCourseRepo extends JpaRepository<DepartmentCourse, Long>{
	List<DepartmentCourse> findByDepartmentCollegeId(Long departmentId);
	void deleteByDepartmentCollegeId(Long departmentId);
}
