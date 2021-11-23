package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
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
	public User createUser(User u) throws UserExistsException{
		User existing = userRepo.findByUsername(u.getUsername());
		if(existing != null) {
			throw new UserExistsException("User already exists!!!");
		}
		return userRepo.save(u);
	}
	
	//findUserById
	public Optional<User> getUserById(Long id) throws UserNotFoundException{
		 Optional<User> user = userRepo.findById(id);
		 if(!user.isPresent()) {
			 throw new UserNotFoundException("User not found in User Repository");
		 }
		 return user;
	}
	
	//updateUserById
	
	public User updateUserById(@PathVariable Long id, User u) throws UserNotFoundException{
		Optional<User> user = userRepo.findById(id);
		 if(!user.isPresent()) {
			 throw new UserNotFoundException("User not found in User Repository, provide the correct user Id");
		 }
		u.setUserId(id);
		return userRepo.save(u);
	}
	
	//deleteUserById
	
	public void deleteUserById(Long id) throws ResponseStatusException {
		Optional<User> user = userRepo.findById(id);
		 if(!user.isPresent()) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in User Repository, provide the correct user Id");
		 }
		userRepo.deleteById(id);
	}
	
	//getUserByUsername
	
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
}
