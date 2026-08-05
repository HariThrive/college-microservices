package com.college.department_college.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.department_college.entity.DepartmentCollege;
import com.college.department_college.repo.DepartmentCollegeRepo;

@Service
public class DepartmentCollegeServiceImpl implements DepartmentCollegeService{
	
	@Autowired
	private DepartmentCollegeRepo departmentCollegeRepo;

	@Override
	public String saveDepartment(DepartmentCollege department) {
		departmentCollegeRepo.save(department);
	
		return "saved succesfuly";
	}

	@Override
	public List<DepartmentCollege> getAllDepartment() {
		
		return departmentCollegeRepo.findAll() ;
	}

	@Override
	public DepartmentCollege saveDepartmentCollege(DepartmentCollege department) {
		
		return departmentCollegeRepo.save(department);
	}

	@Override
	public DepartmentCollege findByDepartmentId(Long departmentId) {
		 return departmentCollegeRepo.findById(departmentId).get();
	}

}
