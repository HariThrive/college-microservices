package com.college.department_college.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.department_college.entity.DepartmentCollege;
import com.college.department_college.mapper.DepartmentCollegeMapper;
import com.college.department_college.repo.DepartmentCollegeRepo;
import com.college.department_college.vo.DepartmentCollegeVO;

@Service
public class DepartmentCollegeServiceImpl implements DepartmentCollegeService {

	private final DepartmentCollegeRepo departmentCollegeRepo;

	private final DepartmentCollegeMapper departmentCollegeMapper;

	public DepartmentCollegeServiceImpl(DepartmentCollegeRepo departmentCollegeRepo,
			DepartmentCollegeMapper departmentCollegeMapper) {

		this.departmentCollegeRepo = departmentCollegeRepo;
		this.departmentCollegeMapper = departmentCollegeMapper;
	}

	@Override
	public String saveDepartment(DepartmentCollege department) {
		departmentCollegeRepo.save(department);

		return "saved succesfuly";
	}

	@Override
	public List<DepartmentCollege> getAllDepartment() {

		return departmentCollegeRepo.findAll();
	}

	@Override
	public DepartmentCollege saveDepartmentCollege(DepartmentCollegeVO department) {

		DepartmentCollege entity = departmentCollegeMapper.toEntity(department);
		return departmentCollegeRepo.save(entity);
	}

	@Override
	public DepartmentCollege findByDepartmentId(Long departmentId) {
		return departmentCollegeRepo.findById(departmentId).get();
	}

}
