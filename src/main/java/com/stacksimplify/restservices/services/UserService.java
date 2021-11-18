package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	//getallUsers
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	//createUser
	public User createUser(User u) {
		return userRepo.save(u);
	}
	
	//findUserById
	public Optional<User> getUserById(Long id) {
		 Optional<User> user = userRepo.findById(id);
		 return user;
	}
	
	//updateUserById
	
	public User updateUserById(@PathVariable Long id, User u) {
		u.setId(id);
		return userRepo.save(u);
	}
	
	//deleteUserById
	
	public void deleteUserById(Long id) {
		if(userRepo.findById(id).isPresent()) {
			userRepo.deleteById(id);
		}
	}
	
	//getUserByUsername
	
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
}
