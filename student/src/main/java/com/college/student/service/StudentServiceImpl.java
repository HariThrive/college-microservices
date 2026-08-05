package com.college.student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.student.client.DepartmentCollegeClient;
import com.college.student.entity.DepartmentCollege;
import com.college.student.entity.Student;
import com.college.student.repo.StudentRepo;
import com.college.student.vo.StudentVO;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired 
	private DepartmentCollegeClient departmentCollegeClient;

	@Override
	public String saveStudent(Student student) {
		studentRepo.save(student);
		return "Student Saved";
	}

	@Override
	public List<StudentVO> getAllStudent() {

		List<Student> studentsInfo = studentRepo.findAll();
		List<StudentVO> allStudents = new ArrayList<StudentVO>();

		for (Student student : studentsInfo) {
			StudentVO tempStudent = new StudentVO();
			tempStudent.setId(student.getId());
			tempStudent.setName(student.getName());
			tempStudent.setAge(student.getAge());
			tempStudent.setEmail(student.getEmail());
			tempStudent.setPhoneNumber(student.getPhoneNumber());
			Long id = student.getDepartmentId();
			if (id != null) {
				DepartmentCollege departmentInfo = departmentCollegeClient.findByDepartmentId(id);
				tempStudent.setDepartmentName(departmentInfo.getDepartmentName());
				tempStudent.setDepartmentId(departmentInfo.getId());

			}

			allStudents.add(tempStudent);

		}
		return allStudents;
	}

	@Override
	public List<DepartmentCollege> getAlldepartment() {
		return departmentCollegeClient.getAllDepartment();
	}

	
	
}
