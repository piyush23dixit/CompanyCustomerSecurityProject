package com.example2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages= {"com.example","com.example2"})
@EnableFeignClients
public class CustomerDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDetailsApplication.class, args);
	}

}
