package com.example2.Services;

import com.example.Exception.ResourceNotFoundException;
import com.example2.Collections.CompanyDTO;
import com.example2.Collections.CustCompDTO;
import com.example2.Collections.Customer;
import com.example2.Collections.CustomerDTO;
import com.example2.Feign.CompanyFeign;
import com.example2.Repository.CustomerRepo;

import jakarta.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerServiceInterface{
	
	@Autowired
	private CustomerRepo custRepo;
	
	@Autowired
	private CompanyFeign companyFeign;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public CustomerDTO addCustomer(CustomerDTO cust) {
		Customer cust1=modelMapper.map(cust,Customer.class);
		custRepo.save(cust1);
		return modelMapper.map(cust1, CustomerDTO.class);
	}
	
	@Override
	public CustCompDTO getCustWithComp(String custId) {
		
		String token =request.getHeader("Authorization");
		
            Customer cust=custRepo.findById(custId).orElseThrow(
    				()-> new ResourceNotFoundException("Customer not find with given id "+custId));
            
           CompanyDTO comDto =companyFeign.getCompany(cust.getCompId(),token);
            
            CustCompDTO dto=modelMapper.map(cust, CustCompDTO.class);
            dto.setCompDTO(comDto);
		return dto;
	}

	@Override
	public List<CustomerDTO> getAllCustomer(String compId) {
		
		MatchOperation match=Aggregation.match(Criteria.where("compId").is(compId));
		ProjectionOperation project = Aggregation.project()
	            .and("_id").as("custId")
	            .and("custName").as("custName")
	            .and("custCode").as("custCode")
	            .and("custAddress").as("custAddress")
	            .and("compId").as("compId");
		
		Aggregation aggregation = Aggregation.newAggregation(match, project);
		
		List<CustomerDTO> customers = mongoTemplate.aggregate(aggregation, "customer", CustomerDTO.class).getMappedResults();

	    return customers;
	}

	@Override
	public CustomerDTO getCustomer(String custId) {
		Customer cust= custRepo.findById(custId).orElseThrow(
				()-> new ResourceNotFoundException("Customer not find with given id "+custId));
		CustomerDTO dto=modelMapper.map(cust, CustomerDTO.class);
		
		return dto;
	}


}
