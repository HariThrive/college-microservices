package com.college.department_college.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.department_college.courseentity.Course;

public interface CourseRepo extends JpaRepository<Course,Long> {

}
