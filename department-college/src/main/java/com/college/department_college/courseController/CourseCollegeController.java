package com.college.department_college.courseController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.college.department_college.courseentity.Course;
import com.college.department_college.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseCollegeController {
	
	@Autowired
	CourseService courseService;

	@GetMapping
	public ModelAndView getAllCourse() {
		ModelAndView mv = new ModelAndView("course");

		List<Course> courseList = courseService.getAllCourses();

		mv.addObject("courses", courseList);  
		mv.addObject("course", new Course());

		return mv;
	}
	
	@GetMapping("/get")
	@ResponseBody
	public List<Course> getAllCourses() {
	    return courseService.getAllCourses();
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> saveCourse(@ModelAttribute Course course){
		Course	saveCourse = courseService.saveCourse(course);
		
		return ResponseEntity.ok(saveCourse);
	}
}
