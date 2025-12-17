package com.example1.Services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Exception.ResourceNotFoundException;
import com.example1.Collection.CompCustDTO;
import com.example1.Collection.Company;
import com.example1.Collection.CompanyDTO;
import com.example1.Collection.CustomerDTO;
import com.example1.Feign.CustomerFeign;
import com.example1.Repository.CompanyRepo;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CompanyServiceImpl implements CompanyServiceInterface{
	
	@Autowired
	private CompanyRepo compRepo;
	
	@Autowired
	private ModelMapper modelMapper; 
	
	@Autowired
	private CustomerFeign customerFeign;
	
	@Autowired
    private HttpServletRequest request;

	@Override
	public CompanyDTO addCompany(CompanyDTO comp) {
		Company comp1=modelMapper.map(comp, Company.class);
		compRepo.save(comp1);
		
		return modelMapper.map(comp1, CompanyDTO.class);

	}

	@Override
	public CompanyDTO getCompany(String compId) {
		Company comp= compRepo.findById(compId).orElseThrow(
						()-> new ResourceNotFoundException("Company does not exists"+compId));
		
		return modelMapper.map(comp, CompanyDTO.class);
	}

	@Override
	public CompCustDTO getAllCustByCompId(String compId) {
		
        String token = request.getHeader("Authorization");
		
		List<CustomerDTO> customer=customerFeign.getAllCustomer(compId, token);
		
		Company company=compRepo.findById(compId).orElseThrow(
				()-> new ResourceNotFoundException("Company does not exists"+compId));
		
		CompCustDTO comp=modelMapper.map(company, CompCustDTO.class);
		comp.setCustomer(customer);
		
		return comp;
	}
	
	

}





