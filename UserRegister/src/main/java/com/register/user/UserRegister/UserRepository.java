package com.register.user.UserRegister;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.register.user.UserRegister.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{

	
}
