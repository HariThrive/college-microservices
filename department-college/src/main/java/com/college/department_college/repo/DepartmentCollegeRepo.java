package com.college.department_college.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.department_college.entity.DepartmentCollege;

@Repository
public interface DepartmentCollegeRepo extends JpaRepository<DepartmentCollege, Long>{

}
