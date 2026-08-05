package com.college.config_college_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigCollegeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigCollegeServerApplication.class, args);
	}

}
