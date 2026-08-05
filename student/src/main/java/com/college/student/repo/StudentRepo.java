package com.college.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.student.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{

}
