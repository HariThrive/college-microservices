package com.college.api_gateway_college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayCollegeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayCollegeApplication.class, args);
	}

}
