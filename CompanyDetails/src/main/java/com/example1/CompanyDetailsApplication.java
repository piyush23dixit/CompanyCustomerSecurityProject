package com.example1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages  = {"com.example","com.example1"}) 
@EnableFeignClients
public class CompanyDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyDetailsApplication.class, args);
	}
	


}
