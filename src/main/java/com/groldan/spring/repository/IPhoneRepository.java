package com.groldan.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groldan.spring.entity.Phone;

@Repository
public interface IPhoneRepository extends JpaRepository<Phone, String> {
	

}
