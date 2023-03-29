
  package com.klizo.Assignment.dto;
  
  public class LoginDto {
	  String username; 
	  String password;
	public String getUsername() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	} 

  }
 