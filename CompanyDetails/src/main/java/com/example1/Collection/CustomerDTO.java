package com.example1.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
	
	private String custId;
	private String custName;
	private String custCode;
	private String custAddress;
	private String compId;

}
