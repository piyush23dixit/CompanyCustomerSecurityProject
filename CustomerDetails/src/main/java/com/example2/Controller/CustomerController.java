package com.example2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example2.Collections.CustCompDTO;
import com.example2.Collections.CustomerDTO;
import com.example2.Services.CustomerServiceInterface;


@RestController
public class CustomerController {
	
	@Autowired
	private CustomerServiceInterface custService;

	
	@PostMapping("/addCust")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO cust){
		CustomerDTO cust1= custService.addCustomer(cust);
		return ResponseEntity.ok(cust1);
	}
	
	
	//get customer with company
	@GetMapping("/cust/comp/{custId}")
	public ResponseEntity<CustCompDTO> getCustWithComp(@PathVariable String custId){
		CustCompDTO dto=custService.getCustWithComp(custId);
		return ResponseEntity.ok(dto);
	}
	
	//get list of customer with company id
	@GetMapping("/cust/{compId}")
	public List<CustomerDTO> getAllCustomer(@PathVariable String compId){
		List<CustomerDTO> list=custService.getAllCustomer(compId);
		return list;
	}
	
	//get customer only
	@GetMapping("/customer/{custId}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String custId){
		CustomerDTO dto=custService.getCustomer(custId);
		return ResponseEntity.ok(dto);
	}
	

}






