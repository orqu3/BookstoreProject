package com.bookstore.admin.controller;

import com.bookstore.admin.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @PostMapping("/customers/check_email")
    public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email){
        if(customerService.isEmailUnique(id, email)){
            return "OK";
        } else {
            return "Duplicated";
        }
    }
}
