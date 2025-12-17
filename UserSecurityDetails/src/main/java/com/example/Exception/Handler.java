package com.example.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Handler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> HandleResourceNotFoundException(ResourceNotFoundException rnfe){
		return new ResponseEntity<String>("Not Found: "+rnfe.getMessage(),HttpStatus.NOT_FOUND);
	}
	
}
