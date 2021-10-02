package com.groldan.spring.repository;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.groldan.spring.AbstractBaseTest;
import com.groldan.spring.entity.Phone;
import com.groldan.spring.entity.User;

public class UserRepositoryTest extends AbstractBaseTest {

	@MockBean
	protected IUserRepository userRepository;

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void createUser() {
		User mockUser = new User();
		mockUser.setName("Gaby");
		mockUser.setPassword("Gaby12");
		mockUser.setEmail("groldan@gmail.com");
		Phone phone = new Phone();
		phone.setCityCode("124567");
		phone.setCountryCode("54");
		Set<Phone> phones = new HashSet<Phone>();
		phones.add(phone);
		mockUser.setPhones(phones);
		Mockito.doNothing().when(userRepository).save(Mockito.any(User.class));
		userRepository.save(mockUser);
		Mockito.verify(userRepository, Mockito.times(1)).save(mockUser);
	}
}