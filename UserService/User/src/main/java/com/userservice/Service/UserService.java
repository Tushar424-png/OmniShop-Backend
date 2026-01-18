package com.userservice.Service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.userservice.Entity.Users;
import com.userservice.Repository.UserRepository;
import com.userservice.dto.UserRequest;
import com.userservice.dto.UserResponse;
import com.userservice.dto.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse save(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        Users user = new Users();
        user.setFullName(userRequest.getFullName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());

        Users savedUser = userRepository.save(user);
        return mapToUserResponse(savedUser);
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    private UserResponse mapToUserResponse(Users user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        // Mapping Cart Items (Entity to DTO)
        if (user.getCartItems() != null) {
            List<CartResponse> cartDtos = user.getCartItems().stream().map(item -> {
                CartResponse dto = new CartResponse();
                dto.setId(item.getId());
                dto.setProductId(item.getProductId());
                dto.setQuantity(item.getQuantity());
                dto.setUserId(user.getId()); // Sirf ID, object nahi
                return dto;
            }).collect(Collectors.toList());
            response.setCartItems(cartDtos);
        }

        // Mapping Wishlist Items (Entity to DTO)
        if (user.getWishlistItems() != null) {
            List<WishlistResponse> wishlistDtos = user.getWishlistItems().stream().map(item -> {
                WishlistResponse dto = new WishlistResponse();
                dto.setId(item.getId());
                dto.setProductId(item.getProductId());
                dto.setUserId(user.getId()); // Sirf ID
                return dto;
            }).collect(Collectors.toList());
            response.setWishlistItems(wishlistDtos);
        }

        return response;
    }
}