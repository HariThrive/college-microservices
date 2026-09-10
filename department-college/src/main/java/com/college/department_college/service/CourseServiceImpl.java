package com.college.department_college.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.department_college.courseentity.Course;
import com.college.department_college.repo.CourseRepo;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseRepo courseRepo;

	@Override
	public Course saveCourse(Course course) {
		return courseRepo.save(course);
	}

	@Override
	public List<Course> getAllCourses() {
		
		return courseRepo.findAll();
	}	
}
