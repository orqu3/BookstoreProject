package com.bookstore.shopping.service;

import com.bookstore.common.entity.AuthenticationType;
import com.bookstore.common.entity.Country;
import com.bookstore.common.entity.Customer;
import com.bookstore.shopping.repository.CountryRepository;
import com.bookstore.shopping.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CountryRepository countryRepository;
    private final CustomerRepository customerRepository;


    public List<Country> listAllCountries() {
        return countryRepository.findAllByOrderByNameAsc();
    }

    public boolean isEmailUnique (String email) {
        Customer customer = customerRepository.findByEmail(email);
        return customer == null;
    }

    public void updateAuthentication(Customer customer, AuthenticationType type) {
        if(!customer.getAuthenticationType().equals(type)) {
            customerRepository.updateAuthenticationType(customer.getId(), type);
        }
    }

}
