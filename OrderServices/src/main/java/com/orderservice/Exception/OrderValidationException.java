package com.orderservice.Exception;

import java.util.List;
import com.orderservice.dto.OrderItemErrorDTO;

public class OrderValidationException extends RuntimeException {

	 private static final long serialVersionUID = 1L; 
    private final List<OrderItemErrorDTO> errors;

    public OrderValidationException(List<OrderItemErrorDTO> errors) {
        this.errors = errors;
    }

    public List<OrderItemErrorDTO> getErrors() {
        return errors;
    }
}

