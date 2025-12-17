package com.example2.Collections;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
	
	@Id
	private String custId;
	
	@NotBlank(message = "Customer Name is Required")
	@Size(min = 1, max = 16, message = "Customer Name must be between 1 to 16 characters")
	private String custName;
	
	@NotBlank(message = "Customer Code is Required")
	@Size(min = 1, max = 10, message = "Customer Code must be between 1 to 10 characters")
	private String custCode;
	
	@NotBlank(message = "Customer Address is Required")
	@Size(min = 1, max = 30, message = "Customer Address must be between 1 to 30 characters")
	private String custAddress;
	
	@NotBlank(message = "Company Address is Required")
	@Size(min = 1, max = 30, message = "Company Address must be between 1 to 30 characters")
	private String compId;

}
