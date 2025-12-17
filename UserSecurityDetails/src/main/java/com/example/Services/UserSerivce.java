package com.example.Services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Collections.User;
import com.example.Collections.UserDTO;
import com.example.Exception.ResourceNotFoundException;
import com.example.Repository.UserRepo;
@Service
public class UserSerivce {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	AuthenticationManager authManager;

	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

	public UserDTO addUser(UserDTO user) {
		String encodedPassword=encoder.encode(user.getPassword());
		User user1=modelMapper.map(user, User.class);
		user1.setPassword(encodedPassword);
		userRepo.save(user1);
		return modelMapper.map(user1, UserDTO.class);
	}

	public UserDTO getUser(String id) {
		User usr = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with given id"+id));
		return modelMapper.map(usr, UserDTO.class);
	}

	public String verify(UserDTO user) {
		Authentication authentication=
				authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		if(authentication.isAuthenticated())
			return jwtService.generateToken(user.getUsername(),user.getRole());
		else
		  return "fail";
	}
	
	

}



















