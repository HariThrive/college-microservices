package com.college.department_college.vo;

import java.util.List;

import lombok.Data;

@Data

public class DepartmentCollegeVO {
	private Long id;
	private String departmentName;
	private String degree;
	private Integer duration;
	private List<CourseVo> courses;
}
