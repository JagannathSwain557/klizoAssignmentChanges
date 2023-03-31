package com.klizo.Assignment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.klizo.Assignment.dto.UserDto;



@Entity(name="UserEntity")
public class UserEntity {
	@Id
	String fastName;
	String lastName;
	String username;
	String password;
	public String getFastName() {
		return fastName;
	}
	public void setFastName(String fastName) {
		this.fastName = fastName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserEntity(String fastName, String lastName, String username, String password) {
		super();
		this.fastName = fastName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	public UserEntity(UserDto userDto) {
		this.fastName = userDto.getFastName();
		this.lastName = userDto.getLastName();
		this.username = userDto.getUsername();
		this.password = userDto.getPassword();
	}
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
