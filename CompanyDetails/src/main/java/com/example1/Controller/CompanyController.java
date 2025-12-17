package com.example1.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example1.Collection.CompCustDTO;
import com.example1.Collection.CompanyDTO;
import com.example1.Services.CompanyServiceInterface;

import jakarta.validation.Valid;



@RestController
public class CompanyController {

	@Autowired
	private CompanyServiceInterface companyService;
	
	@PostMapping("/addComp")
	public ResponseEntity<CompanyDTO> addCompany(@RequestBody @Valid CompanyDTO comp){
		CompanyDTO comp1=companyService.addCompany(comp);
		return ResponseEntity.ok().body(comp1);
	}
	//to get company by company id
	@GetMapping("/comp/{compId}")
	public CompanyDTO getCompany(@PathVariable String compId){

		CompanyDTO comp=companyService.getCompany(compId);
		return comp;
	}
	
	//to get customer list by company Id
	@GetMapping("/comp/cust/{compId}")
	public ResponseEntity<CompCustDTO> getAllCustByCompId(@PathVariable String compId) {
		CompCustDTO comp=companyService.getAllCustByCompId(compId);
		return ResponseEntity.ok(comp);
	}

	
	

}








