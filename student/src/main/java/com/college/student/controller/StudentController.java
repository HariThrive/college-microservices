package com.college.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.college.student.entity.DepartmentCollege;
import com.college.student.entity.Student;
import com.college.student.service.StudentService;
import com.college.student.vo.StudentVO;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping
	@ResponseBody
	public String addStudent(@RequestBody Student student) {
		String result = studentService.saveStudent(student);
		return result;
	}
	
	@GetMapping
	public ModelAndView gotoStudent() {
	    ModelAndView mv = new ModelAndView("student");
	    mv.addObject("student", new Student());
	    mv.addObject("students", studentService.getAllStudent());
	    mv.addObject("departments", studentService.getAlldepartment());
	    return mv;
	}

	@GetMapping("/all")
	@ResponseBody
	public List<StudentVO> getAllStudent() {
		return studentService.getAllStudent();
	}

}
