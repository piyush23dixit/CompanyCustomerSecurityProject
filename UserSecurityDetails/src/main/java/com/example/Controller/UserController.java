package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.Collections.UserDTO;
import com.example.Configure.JwtTokenValidator;
import com.example.Services.UserSerivce;

@RestController
public class UserController {
	
	@Autowired
	private UserSerivce userService;
	
	@Autowired
    private JwtTokenValidator jwtTokenValidator;
	
	@PostMapping("/addUser")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user){
		UserDTO user1=userService.addUser(user);
		return ResponseEntity.ok(user1);
	}
	
	@GetMapping("/admin/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> getUserByAdmin(@PathVariable String id){
		
		UserDTO user=userService.getUser(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/user/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<UserDTO> getUser(@PathVariable String id){
		
		UserDTO user=userService.getUser(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody UserDTO user){
		return userService.verify(user);
		
	}
	
	@GetMapping("/start")
	public ResponseEntity<String> start(@RequestHeader("Authorization") String authHeader) {
	    if (!jwtTokenValidator.validateAndSetAuthentication(authHeader)) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
	    }

	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    return ResponseEntity.ok("Token is valid, user authenticated: " + username);
	}

	

}







