package com.college.student.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.student.client.DepartmentCollegeClient;
import com.college.student.entity.DepartmentCollege;
import com.college.student.entity.Student;
import com.college.student.repo.StudentRepo;
import com.college.student.vo.StudentVO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired 
	private DepartmentCollegeClient departmentCollegeClient;
	
	private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Override
	public String saveStudent(Student student) {
		studentRepo.save(student);
		return "Student Saved";
	}

	@Override
	@CircuitBreaker(
		    name = "departmentService",fallbackMethod = "departmentFallback"
		)
	public List<StudentVO> getAllStudent() {
		log.info("getALLStudent method started{}","StudentServiceImpl");
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

	@CircuitBreaker(
		    name = "departmentService",
		    fallbackMethod = "departmentFallback"
		)
	@Override
	public List<DepartmentCollege> getAlldepartment() {
		return departmentCollegeClient.getAllDepartment();
	}
	
	@Override
	public List<StudentVO> departmentFallback(Exception e) {

	    log.error(
	        "Department Service is unavailable. Executing fallback",
	        e
	    );

	    return new ArrayList<>();
	}
	
	
}
