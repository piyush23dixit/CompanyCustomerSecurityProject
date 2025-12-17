package com.example1.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example1.Collection.CustomerDTO;

@FeignClient(name="CustomerDetails", url="localhost:8086")
public interface CustomerFeign {

	@GetMapping("/cust/{compId}")
	List<CustomerDTO> getAllCustomer(@PathVariable String compId, 
			@RequestHeader("Authorization") String token);
}
