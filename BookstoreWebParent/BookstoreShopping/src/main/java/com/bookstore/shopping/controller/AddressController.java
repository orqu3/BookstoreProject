package com.bookstore.shopping.controller;

import com.bookstore.common.entity.Address;
import com.bookstore.common.entity.Customer;
import com.bookstore.shopping.service.AddressService;
import com.bookstore.shopping.service.CustomerService;
import com.bookstore.shopping.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AddressController {

    private final AddressService addressService;

    private final CustomerService customerService;


    public AddressController(AddressService addressService, CustomerService customerService) {
        this.addressService = addressService;
        this.customerService = customerService;
    }

    @GetMapping("/address_book")
    public String showAddressBook(Model model, HttpServletRequest request) {
        Customer customer = getAuthenticatedCustomer(request);
        List<Address> listAddresses = addressService.listAddressBook(customer);

        boolean usePrimaryAddressAsDefault = true;
        for (Address address : listAddresses) {
            if(address.isDefaultForShipping()) {
                usePrimaryAddressAsDefault = false;
                break;
            }
        }



        model.addAttribute("listAddresses",listAddresses);
        model.addAttribute("customer",customer);
        model.addAttribute("usePrimaryAddressAsDefault",usePrimaryAddressAsDefault);




        return "/address_book/addresses";
    }

    private Customer getAuthenticatedCustomer(HttpServletRequest request) {
        String email = Utility.getEmailOfAuthenticatedCustomer(request);
        return customerService.getCustomerByEmail(email);
    }
}
