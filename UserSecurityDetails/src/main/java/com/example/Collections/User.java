package com.example.Collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	
	@Id
	private String id;
	
	@NotBlank(message = "Username is Required")
	@Size(min = 1, max = 16, message = "Username must be between 1 to 16 characters")
	private String username;
	
	
	@NotBlank(message = "Password is Required")
	@Size(min = 1, max = 16, message = "Password Name must be between 1 to 16 characters")
	private String password;
	
	 @NotBlank(message = "Role is Required")
	 private String role; 

}
