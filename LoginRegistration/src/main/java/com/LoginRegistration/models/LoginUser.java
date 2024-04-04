package com.LoginRegistration.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginUser {

	@NotEmpty(message="An email is required for login!")
	@Email(message="the email you entered must be a valid format!")
	private String email;
	
	@NotEmpty(message="you need to enter a password!")
	@Size(min=8,max=100,message="your password has to be between 8 and 45 characters long!")
	private String password;
	
	public LoginUser() {}

	public String getEmail() {
		return email;
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
	
}
