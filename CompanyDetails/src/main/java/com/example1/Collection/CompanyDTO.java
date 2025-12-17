package com.example1.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDTO {

	String compId;
	String compName;
	String compCode;
	String compAddress;
}
