package com.college.department_college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DepartmentCollegeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentCollegeApplication.class, args);
	}

}
