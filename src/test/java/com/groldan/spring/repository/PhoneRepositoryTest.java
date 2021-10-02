package com.groldan.spring.repository;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.groldan.spring.AbstractBaseTest;
import com.groldan.spring.entity.Phone;
import com.groldan.spring.entity.User;

public class PhoneRepositoryTest extends AbstractBaseTest {

	@MockBean
	protected IPhoneRepository phoneRepository;

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void createUser() {
	    Phone phone = new Phone();
		phone.setCityCode("124567");
		phone.setCountryCode("54");
		
		Mockito.doNothing().when(phoneRepository.save(Mockito.any(Phone.class)));
		phoneRepository.save(phone);
		Mockito.verify(phoneRepository, Mockito.times(1)).save(phone);
	}
}