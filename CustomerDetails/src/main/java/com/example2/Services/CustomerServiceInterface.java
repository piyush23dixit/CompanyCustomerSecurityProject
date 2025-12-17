package com.example2.Services;

import java.util.List;

import com.example2.Collections.CustCompDTO;
import com.example2.Collections.CustomerDTO;

public interface CustomerServiceInterface {

	public CustomerDTO addCustomer(CustomerDTO cust);

	CustCompDTO getCustWithComp(String custId);

	List<CustomerDTO> getAllCustomer(String compId);

	CustomerDTO getCustomer(String custId);

}
