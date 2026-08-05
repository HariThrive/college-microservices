package com.college.department_college.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.college.department_college.entity.DepartmentCollege;
import com.college.department_college.service.DepartmentCollegeService;

@Controller
@RequestMapping("/department")
public class DepartmentCollegeController {
	
	@Autowired
	private DepartmentCollegeService departmentCollegeService;
	
//	@PostMapping
//	public String saveDepartment(@RequestBody DepartmentCollege department) {
//		String result = departmentCollegeService.saveDepartment(department);
//		
//		return result;
//	}

	@GetMapping("/get")
	@ResponseBody
	public List<DepartmentCollege> getAllDepartment(){
		return departmentCollegeService.getAllDepartment();
	}
	
	@GetMapping
	public ModelAndView gotoDepartment() {

	    ModelAndView mv = new ModelAndView("department");

	    mv.addObject("department", new DepartmentCollege());

	    mv.addObject("departments",
	            departmentCollegeService.getAllDepartment());

	    return mv;
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> saveDepartment(@ModelAttribute DepartmentCollege department) {

	    DepartmentCollege savedDepartment = departmentCollegeService.saveDepartmentCollege(department);

	    return ResponseEntity.ok(savedDepartment);
	}
	
	@GetMapping("/student/{departmentId}")
	@ResponseBody
	public DepartmentCollege findByDepartmentId(@PathVariable Long departmentId) {
	    return departmentCollegeService.findByDepartmentId(departmentId);
	}
}
