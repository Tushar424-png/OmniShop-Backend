package com.userservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.Service.UserService;
import com.userservice.dto.UserRequest;
import com.userservice.dto.UserResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
  
	private final UserService userservice;
	public UserController (UserService userservice) {
		this.userservice=userservice;
	}
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse add(@Valid @RequestBody UserRequest userRequest) {
		return userservice.save(userRequest);
	}
	@GetMapping("/getall")
	@ResponseStatus(HttpStatus.OK)
	public List<UserResponse>usereponse(){
		return userservice.getAll();
	}
	
}
