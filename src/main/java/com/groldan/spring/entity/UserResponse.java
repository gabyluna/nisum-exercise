package com.groldan.spring.entity;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

	@JsonProperty("id")
	private UUID id;
	@JsonProperty("created")
	private Date created;
	@JsonProperty("modified")
	private Date modified;
	@JsonProperty("last_login")
	private Date lastLogin;
	@JsonProperty("token")
	private String token;
	@JsonProperty("is_active")
	private boolean isActive;

	public UUID getId() {
		return id;
	}

	@JsonAnySetter
	public void setId(UUID id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	@JsonAnySetter
	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	@JsonAnySetter
	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	@JsonAnySetter
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	@JsonAnySetter
	public void setToken(String token) {
		this.token = token;
	}

	public boolean isActive() {
		return isActive;
	}

	@JsonAnySetter
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
