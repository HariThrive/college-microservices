package com.college.department_college.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DepartmentCollege {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="department_name")
	private String departmentName;
	
	@Column(name="degree")
	private String degree;
	
	@Column(name="duration")
	private Integer duration;
	
}
