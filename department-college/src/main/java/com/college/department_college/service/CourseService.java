package com.college.department_college.service;

import java.util.List;

import com.college.department_college.courseentity.Course;

public interface CourseService {

	Course saveCourse(Course course);

	List<Course> getAllCourses();

}
