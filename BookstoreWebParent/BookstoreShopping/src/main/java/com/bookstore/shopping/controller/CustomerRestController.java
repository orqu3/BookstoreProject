package com.bookstore.shopping.controller;

import com.bookstore.shopping.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @PostMapping("/customers/check_unique_email")
    public String checkDuplicateEmail(@Param("email") String email) {
        return customerService.isEmailUnique(email) ? "OK" : "Duplicated";
    }
}
