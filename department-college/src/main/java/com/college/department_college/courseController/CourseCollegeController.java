package com.college.department_college.courseController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.college.department_college.courseentity.Course;

@Controller
@RequestMapping("/course")
public class CourseCollegeController {

	@GetMapping
	public ModelAndView getAllCourse() {
		ModelAndView mv = new ModelAndView("course");

		List<Course> courseList = new ArrayList<>();
		Course sample = new Course();
		sample.setId(1L);
		sample.setCourseName("JAVA");
		sample.setDescription("PROGRAMMING LANGUAGE");
		courseList.add(sample);

		mv.addObject("courses", courseList);  
		mv.addObject("course", new Course());

		return mv;
	}
}
