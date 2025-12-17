package com.example1.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="company")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {

	@Id
	String compId;
	
	@NotBlank(message = "Company Name is Required")
	@Size(min = 1, max = 16, message = "Company Name must be between 1 to 16 characters")
	String compName;
	
	@NotBlank(message = "Company Code is Required")
	@Size(min = 1, max = 16, message = "Company Code must be between 1 to 16 characters")
	String compCode;
	
	@NotBlank(message = "Company Address is Required")
	@Size(min = 1, max = 16, message = "Company Address must be between 1 to 16 characters")
	String compAddress;
}




