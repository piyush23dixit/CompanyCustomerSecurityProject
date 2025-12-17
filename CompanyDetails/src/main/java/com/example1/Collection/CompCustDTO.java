package com.example1.Collection;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompCustDTO {
	
	private String compId;
	private String compName;
	private String compCode;
	private String compAddress;
	private List<CustomerDTO> customer;

}
