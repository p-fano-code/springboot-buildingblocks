package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.OrderHateoasController;
import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userService;

	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException{
		List<User> allusers = userService.getAllUsers();
		for(User user : allusers) {
			//selfLinks
			Long userid = user.getUserId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selfLink);
			
			//relationship with orders
			CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userid);
			Link ordersLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
		}
		//selfLink for getAllUsers
		Link selflinkgetAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		CollectionModel<User> finalResources = new CollectionModel<User>(allusers, selflinkgetAllUsers);
		return finalResources;
	 }

	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable @Min(1) Long id) {
		try {
			Optional<User> user = userService.getUserById(id);
			User user2 = user.get();
			Long userid = user2.getUserId();
			Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user2.add(selflink);
			EntityModel<User> finalResource = new EntityModel<User>(user2);
			return finalResource;
			
		} catch (UserNotFoundException ex) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

}
