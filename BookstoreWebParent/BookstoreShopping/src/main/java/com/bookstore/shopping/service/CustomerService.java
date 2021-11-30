package com.bookstore.shopping.service;

import com.bookstore.common.entity.Country;
import com.bookstore.common.entity.Customer;
import com.bookstore.shopping.repository.CountryRepository;
import com.bookstore.shopping.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CountryRepository countryRepo;

    private final CustomerRepository customerRepo;

    public CustomerService(CountryRepository countryRepo, CustomerRepository customerRepo) {
        this.countryRepo = countryRepo;
        this.customerRepo = customerRepo;
    }

    public List<Country> listAllCountries() {
        return countryRepo.findAllByOrderByNameAsc();
    }

    public boolean isEmailUnique (String email) {
        Customer customer = customerRepo.findByEmail(email);
        return customer == null;
    }

}
