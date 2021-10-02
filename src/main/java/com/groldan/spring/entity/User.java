package com.groldan.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.ToString;

@Entity
@Table(name = "user")
@ToString
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
	
	private String name;
	
	private String email;
	
	private String password;

	@OneToMany(mappedBy = "user")
	private Set<Phone> phones;
	
	@CreationTimestamp
	private Date created;
	
	@UpdateTimestamp
	private Date modified;
	
	@CreationTimestamp
	@Column(name = "last_login")
	private Date lastLogin;	

	private String token;
	
	@Column(name = "ENABLED", nullable = false)
	private boolean isActive;
	
	public User() {}
	
	public UUID getId(){
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public Set<Phone> getPhones(){
		return phones;
	}
	
	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

}
