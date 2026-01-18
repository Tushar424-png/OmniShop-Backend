package com.AuthService.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class AuthDTO {
	    @NotBlank(message = "Name cannot be empty")
        private String fullName; 
	
	    @Column(unique = true)
	    private String email;   

	    private String password; 
	    
	    @NotBlank(message = "Address is important for order delievery")
	    private String address;

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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
        
		public AuthDTO() {
			super();
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		@Override
		public String toString() {
			return "AuthDTO [fullName=" + fullName + ", email=" + email + ", password=" + password + ", address="
					+ address + "]";
		}
	    
	}


