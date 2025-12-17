package com.example.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Collections.User;
import com.example.Repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	public UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.print(username);
		User user=userRepo.findByUsername(username).orElseThrow(
				()-> new UsernameNotFoundException("User with username " + username + " not found"));
		
	
		return new UserPrincipal(user);
	}

}
