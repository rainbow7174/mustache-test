package com.mugen.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User extends AbstractEntity {
	
	@Column(nullable=false, length=20, unique=true)
	@JsonProperty
	private String userid;
	
	@Column(nullable=false, length=20)
	private String password;
	
	@JsonProperty
	private String username;
	
	@JsonProperty
	private String email;
	
	public boolean matchId(Long newId) {
		if(newId == null) {
			return false;
		}
		return newId.equals(getId());
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean matchPassword(String newPassword) {
		if(newPassword == null) {
			return false;
		}
		return newPassword.equals(password);
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void update(User newUser) {
		this.password = newUser.password;
		this.username = newUser.username;
		this.email = newUser.email;
	}
	
	@Override
	public String toString() {
		return "User [" + super.toString() + ", userid=" + userid + ", password=" + password + ", username=" + username + ", email=" + email
				+ "]";
	}
}
