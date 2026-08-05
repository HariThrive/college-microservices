package com.college.service_registry_college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryCollegeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryCollegeApplication.class, args);
	}

}
