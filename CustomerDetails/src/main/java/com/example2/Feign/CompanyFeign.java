package com.example2.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example2.Collections.CompanyDTO;

@FeignClient(name="CompanyDetails", url="localhost:8085")
public interface CompanyFeign {
	
	@GetMapping("/comp/{compId}")
	public CompanyDTO getCompany(@PathVariable String compId,
			@RequestHeader("Authorization") String token);

}
