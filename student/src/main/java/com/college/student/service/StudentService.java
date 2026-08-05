package com.college.student.service;

import java.util.List;

import com.college.student.entity.DepartmentCollege;
import com.college.student.entity.Student;
import com.college.student.vo.StudentVO;

public interface StudentService {

	String saveStudent(Student student);

	List<StudentVO> getAllStudent();

	List<DepartmentCollege> getAlldepartment();

}
