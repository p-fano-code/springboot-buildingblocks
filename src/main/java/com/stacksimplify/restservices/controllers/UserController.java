package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();		
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User u) {
		return userService.createUser(u);
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		return userService.getUserById(id);		
	}
	
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User u) {
		return userService.updateUserById(id, u);
	}
	
	@DeleteMapping("users/{id}")
	public void deleteUserById(@PathVariable Long id) {
		 userService.deleteUserById(id);
	}
	
	@GetMapping("users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}
}
