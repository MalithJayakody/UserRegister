package com.register.user.UserRegister;

import com.register.user.UserRegister.controllers.UserController;
import com.register.user.UserRegister.models.User;
import javafx.application.Application;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRegisterApplicationTest {

	@Autowired
	private UserController controller;
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void contextLoadsTest() {
		assertNotNull(controller);
	}
	@Test
	void getAllUsersAPI(){
		List<User> users = testRestTemplate.getForObject("http://localhost:" + port + "/UserRegister/user", List.class);
		assertTrue(users.size()>0);
	}
	@Test
	void getUserAPI(){
		User user= testRestTemplate.getForObject("http://localhost:" + port + "/UserRegister/user/47",User.class);
		assertNotNull(user);
	}
	@Test
	void insertUserAPI(){
		User newUser = new User("TEST USER", 10, 1000, "Y");
		String s = testRestTemplate.postForObject("http://localhost:" + port + "/UserRegister/user", newUser, String.class);
		assertTrue(s.contains("Y"));
	}
	@Test
	void deleteUser(){
		int countBeforeDelete = getUserCount();
		testRestTemplate.delete("http://localhost:" + port + "/UserRegister/user/23");
		assertEquals(countBeforeDelete-1,getUserCount());
	}

	int getUserCount(){
		return controller.getAllUsers().size();
	}
}
