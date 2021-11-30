package com.bookstore.shopping.controller;


import com.bookstore.common.entity.Country;
import com.bookstore.common.entity.Customer;
import com.bookstore.shopping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/registration")
    public String showRegisterForm(Model model) {
       List<Country> listCountries = service.listAllCountries();

       model.addAttribute("listCountries",listCountries);
       model.addAttribute("pageTitle","Customer Registration");
       model.addAttribute("customer",new Customer());

       return  "/register/register_form";
    }

}
