package com.gearing.loginandregistration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.gearing.loginandregistration.models.LoginUser;
import com.gearing.loginandregistration.models.User;
import com.gearing.loginandregistration.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public User register(User newUser, BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
			return null;
		}
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			return null;
		}
		
		return null;
	}
	
	public User login(LoginUser newLoginObject, BindingResult result) {
		
		return null;
	}
}
