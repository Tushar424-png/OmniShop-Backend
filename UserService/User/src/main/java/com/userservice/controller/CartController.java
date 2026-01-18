package com.userservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.userservice.Service.CartService;
import com.userservice.dto.CartRequest;
import com.userservice.dto.CartResponse;


@RestController
@RequestMapping("/Cart")
public class CartController {
private final CartService cartservice;
	
	public CartController(CartService cartservice) {
		this.cartservice=cartservice;
	}
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
	public CartResponse add(@RequestBody CartRequest cartRequest) {
    	return cartservice.addToCart(cartRequest);
    }
    @GetMapping("/getall")
    @ResponseStatus(HttpStatus.OK)
    public List<CartResponse>getAll(){
    	return cartservice.getall();
    }
	
}
