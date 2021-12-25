package com.bookstore.shopping.controller;


import com.bookstore.common.entity.Customer;
import com.bookstore.common.exception.CustomerNotFoundException;
import com.bookstore.common.exception.OrderNotFoundException;
import com.bookstore.shopping.service.CustomerService;
import com.bookstore.shopping.service.OrderService;
import com.bookstore.shopping.util.order.OrderReturnRequest;
import com.bookstore.shopping.util.order.OrderReturnResponse;
import com.bookstore.shopping.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;
    private final CustomerService customerService;

    @PostMapping("/orders/return")
    public ResponseEntity<?> handle(@RequestBody OrderReturnRequest returnRequest, HttpServletRequest servletRequest) {
        Customer customer = null;
        
        try {
            customer = getAuthenticatedCustomer(servletRequest);
        } catch (CustomerNotFoundException ex) {
            return new ResponseEntity<>("Authentication required", HttpStatus.BAD_REQUEST);
        }

        try {
            orderService.setOrderReturnRequested(returnRequest, customer);
        } catch (OrderNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(new OrderReturnResponse(returnRequest.getOrderId()), HttpStatus.OK);
    }


    private Customer getAuthenticatedCustomer(HttpServletRequest request) throws CustomerNotFoundException {
        String email = Utility.getEmailOfAuthenticatedCustomer(request);
        if (email == null) {
            throw new CustomerNotFoundException("No authenticated customer");
        }

        return customerService.getCustomerByEmail(email);
    }
}
