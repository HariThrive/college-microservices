package com.college.department_college.mapper;

import org.mapstruct.Mapper;

import com.college.department_college.entity.DepartmentCollege;
import com.college.department_college.vo.DepartmentCollegeVO;

@Mapper(componentModel ="spring" )
public interface DepartmentCollegeMapper {

	 DepartmentCollege toEntity(DepartmentCollegeVO vo);

	    DepartmentCollegeVO toVO(DepartmentCollege entity);
}
