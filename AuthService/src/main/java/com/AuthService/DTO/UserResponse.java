package com.AuthService.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserResponse {
    @NotBlank(message = "Name cannot be empty")
    private String fullName;

    @Email(message = "Invalid email format")
    private String email;
     
    @NotBlank(message = "Address is important for order delievery")
    private String address;
    
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserRequest [fullName=" + fullName + ", email=" + email + ",  Address="
				+ address;
	}


}

