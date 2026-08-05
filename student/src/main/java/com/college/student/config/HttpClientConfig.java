package com.college.student.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.college.student.client.DepartmentCollegeClient;

@Configuration
public class HttpClientConfig {
	 @Value("${department.service.url}")
	    private String departmentServiceUrl;

	    @Bean
	    public DepartmentCollegeClient departmentCollegeClient() {
	        RestClient restClient = RestClient.builder()
	                .baseUrl(departmentServiceUrl)
	                .build();

	        RestClientAdapter adapter = RestClientAdapter.create(restClient);
	        HttpServiceProxyFactory factory = HttpServiceProxyFactory
	                .builderFor(adapter)
	                .build();

	        return factory.createClient(DepartmentCollegeClient.class);
	    }
}
