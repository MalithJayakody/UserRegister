package com.register.user.UserRegister.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.register.user.UserRegister.models.User;
import com.register.user.UserRegister.services.UserService;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getAllUsers() {

		return userService.getAllUsers();
	}
	
	@GetMapping("{id}")
	public Optional<User> getUser(@PathVariable int id) {
		Optional<User> user =userService.getUser(id);
		if(user==null){
			System.out.println("NULL");
			throw new EntityNotFoundException();
		}
		return user;
	}
	
	@PostMapping
	public String insertUser(@RequestBody User user) {
		if(userService.insertUser(user)==1) {
			return "{status:Y}";
		}else {
			return "{status:N}";
		}
	}
	
	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}
	
}
