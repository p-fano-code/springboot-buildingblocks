package com.stacksimplify.restservices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@GetMapping("/{userid}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
		
		Optional<User> user = userRepo.findById(userid);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		
		List<Order> allOrders = user.get().getOrder();
		CollectionModel<Order>finalResources = new CollectionModel<Order>(allOrders);
		
		return finalResources;
		
		 		
	}
}
