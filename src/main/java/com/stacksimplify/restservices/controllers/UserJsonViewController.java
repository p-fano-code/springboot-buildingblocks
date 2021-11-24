package com.stacksimplify.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.entities.Views;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@Validated
@RequestMapping(value = "/jsonview/users")
public class UserJsonViewController {

	@Autowired
	private UserService userService;
	
	//getUserById -- external
	@JsonView(Views.External.class)
	@GetMapping("/external/{id}")
	public Optional<User> getUserById(@PathVariable @Min(1) Long id) {
		try {
			return userService.getUserById(id);			
		} catch (UserNotFoundException ex) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	//getUserById -- internal
	@JsonView(Views.Internal.class)
	@GetMapping("/internal/{id}")
	public Optional<User> getUserById2(@PathVariable @Min(1) Long id) {
		try {
			return userService.getUserById(id);			
		} catch (UserNotFoundException ex) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
}
