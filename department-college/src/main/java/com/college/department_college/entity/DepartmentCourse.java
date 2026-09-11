package com.college.department_college.entity;

import com.college.department_college.courseentity.Course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class DepartmentCourse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="department")
	private DepartmentCollege departmentCollege;
	
	@ManyToOne
	@JoinColumn(name="course")
	private Course course;
	
	@Column(name="status")
	private String status;
}
