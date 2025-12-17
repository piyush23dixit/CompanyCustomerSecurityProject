package com.example2.Collections;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustCompDTO {
	
	private String CustId;
	private String custName;
	private String custCode;
	private String custAddress;
	private String compId;
	private CompanyDTO compDTO;

}
