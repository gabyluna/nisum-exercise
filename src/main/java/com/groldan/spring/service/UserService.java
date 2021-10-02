package com.groldan.spring.service;

import java.util.List;

import com.groldan.spring.entity.User;
import com.groldan.spring.entity.UserResponse;

public interface UserService {
	public UserResponse createUser(User user);
	public User findUserByEmail(String email);
	public List<User> getUsers();
}
