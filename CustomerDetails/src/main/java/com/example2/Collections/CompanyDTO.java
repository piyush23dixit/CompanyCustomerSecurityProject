package com.example2.Collections;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDTO {

	private String compId;
	private String compName;
	private String compCode;
	private String compAddress;
}
