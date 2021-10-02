package com.groldan.spring.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groldan.spring.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
	public User findByEmail(String email);
}
