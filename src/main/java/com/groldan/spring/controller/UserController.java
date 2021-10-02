package com.groldan.spring.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.groldan.spring.entity.Phone;
import com.groldan.spring.entity.User;
import com.groldan.spring.entity.UserResponse;
import com.groldan.spring.exceptions.ErrorException;
import com.groldan.spring.repository.IPhoneRepository;
import com.groldan.spring.repository.IUserRepository;
import com.groldan.spring.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@Validated
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(
		    value = "/user", 
		    method = RequestMethod.POST,
		    produces = {"application/json"})
	
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody User user) throws Exception{
		User userFound = userService.findUserByEmail(user.getEmail());
		if(userFound != null){
			throw new ErrorException("El correo ya se encuentra registrado", "0");
		}
		
		String token = getJWTToken(user.getName());
		user.setToken(token);
		return new ResponseEntity<UserResponse>(userService.createUser(user), HttpStatus.CREATED);			
	}
	
	@RequestMapping(
		    value = "/users", 
		    method = RequestMethod.GET,
		    produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public List<User> getUsers(){
		return userService.getUsers();			
	}
	
	@RequestMapping({ "/hello" })
	public String firstPage() {
	return "Hello World";
	}
	
	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

}
