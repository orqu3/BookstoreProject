package com.bookstore.shopping.service;

import com.bookstore.common.entity.Country;
import com.bookstore.common.entity.Customer;
import com.bookstore.shopping.repository.CountryRepository;
import com.bookstore.shopping.repository.CustomerRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CustomerService {

    private final CountryRepository countryRepo;

    private final CustomerRepository customerRepo;

    final PasswordEncoder passwordEncoder;

    public CustomerService(CountryRepository countryRepo, CustomerRepository customerRepo, PasswordEncoder passwordEncoder) {
        this.countryRepo = countryRepo;
        this.customerRepo = customerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Country> listAllCountries() {
        return countryRepo.findAllByOrderByNameAsc();
    }

    public boolean isEmailUnique (String email) {
        Customer customer = customerRepo.findByEmail(email);
        return customer == null;
    }

    public void registerCustomer(Customer customer) {
        encodePassword(customer);
        customer.setEnable(false);
        customer.setCreatedTime(new Date());

       String randomCode = RandomString.make(64);
       customer.setVerificationCode(randomCode);

        customerRepo.save(customer);
    }

    private void encodePassword(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
    }

}
