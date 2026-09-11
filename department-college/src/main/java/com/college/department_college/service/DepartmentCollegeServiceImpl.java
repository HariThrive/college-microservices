package com.college.department_college.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.department_college.courseentity.Course;
import com.college.department_college.entity.DepartmentCollege;
import com.college.department_college.entity.DepartmentCourse;
import com.college.department_college.mapper.DepartmentCollegeMapper;
import com.college.department_college.repo.DepartmentCollegeRepo;
import com.college.department_college.repo.DepartmentCourseRepo;
import com.college.department_college.vo.CourseVo;
import com.college.department_college.vo.DepartmentCollegeVO;

import jakarta.transaction.Transactional;

@Service
public class DepartmentCollegeServiceImpl implements DepartmentCollegeService {

	private final DepartmentCollegeRepo departmentCollegeRepo;

	private final DepartmentCollegeMapper departmentCollegeMapper;
	
	@Autowired
	DepartmentCourseRepo departmentCourseRepo;

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
	@Transactional
	public DepartmentCollege saveDepartmentCollege(DepartmentCollegeVO department) {

		DepartmentCollege entity = departmentCollegeMapper.toEntity(department);
		List<DepartmentCourse>  departmentCourses = new ArrayList<>();
		
		if (department.getId() != null) {
		    departmentCourseRepo.deleteByDepartmentCollegeId(department.getId());
		}
		
		DepartmentCollege departmentCollege = departmentCollegeRepo.save(entity);
		
		if (department.getCourses() != null) {
			for(CourseVo course : department.getCourses()) {
			    if (course.getId() != null) {
					DepartmentCourse departmentCourse = new DepartmentCourse();
					DepartmentCollege dept = new DepartmentCollege();
			        dept.setId(departmentCollege.getId());
			        Course courseEntity = new Course();
			        courseEntity.setId(course.getId());
			        
			        departmentCourse.setDepartmentCollege(dept);
			        departmentCourse.setCourse(courseEntity);
					departmentCourse.setStatus("A");
					departmentCourses.add(departmentCourse);
			    }
			}
		}
		
		departmentCourseRepo.saveAll(departmentCourses);
		
		return departmentCollege ;
	}

	@Override
	public DepartmentCollege findByDepartmentId(Long departmentId) {
		return departmentCollegeRepo.findById(departmentId).get();
	}

	@Override
	public DepartmentCollegeVO getDepartmentWithCourses(Long departmentId) {
		DepartmentCollege entity = departmentCollegeRepo.findById(departmentId).orElse(null);
		if (entity == null) {
			return null;
		}

		DepartmentCollegeVO vo = departmentCollegeMapper.toVO(entity);

		List<DepartmentCourse> deptCourses = departmentCourseRepo.findByDepartmentCollegeId(departmentId);
		List<CourseVo> courseVos = new ArrayList<>();
		for (DepartmentCourse dc : deptCourses) {
			if (dc.getCourse() != null && "A".equals(dc.getStatus())) {
				CourseVo cVo = new CourseVo();
				cVo.setId(dc.getCourse().getId());
				cVo.setCourseName(dc.getCourse().getCourseName());
				cVo.setDescription(dc.getCourse().getDescription());
				courseVos.add(cVo);
			}
		}
		vo.setCourses(courseVos);

		return vo;
	}

}
