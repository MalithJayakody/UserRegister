package com.register.user.UserRegister.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.user.UserRegister.UserRepository;
import com.register.user.UserRegister.models.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	public Optional<User> getUser(int id) {
		return userRepository.findById(id);
	}
	
	public int insertUser(User user) {
		int status =0;
		User newUser = userRepository.save(user);
		if(newUser!=null) {
			status =1;
		}
		return status;
	}
	
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
}
