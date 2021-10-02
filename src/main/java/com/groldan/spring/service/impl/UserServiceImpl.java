package com.groldan.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.groldan.spring.entity.Phone;
import com.groldan.spring.entity.User;
import com.groldan.spring.entity.UserResponse;
import com.groldan.spring.repository.IPhoneRepository;
import com.groldan.spring.repository.IUserRepository;
import com.groldan.spring.service.UserService;
import com.groldan.spring.validations.Validation;

@Component
public class UserServiceImpl implements UserService {
	@Autowired
	IUserRepository userRepository;

	@Autowired
	IPhoneRepository phoneRepository;

	public UserResponse createUser(User user) {
		if (!StringUtils.isEmpty(user.getEmail())) {
			Validation.isValidEmail(user.getEmail(), this);
		}

		if (!StringUtils.isEmpty(user.getPassword())) {
			Validation.isValidPassword(user.getPassword(), this);
		}

		User response = userRepository.save(user);
		for (Phone phone : user.getPhones()) {
			phone.setUser(user);
			phoneRepository.save(phone);
		}

		return mapUserResponse(userRepository.save(user));

	}

	private UserResponse mapUserResponse(User user) {
		UserResponse response = new UserResponse();
		response.setId(user.getId());
		response.setActive(user.isActive());
		response.setCreated(user.getCreated());
		response.setModified(user.getModified());
		response.setLastLogin(user.getLastLogin());
		response.setToken(user.getToken());
		return response;
	}

	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	public List<User> getUsers() {	
		return userRepository.findAll();
	}

}
