package com.bookstore.admin.service;

import com.bookstore.admin.exception.CustomerNotFoundException;
import com.bookstore.admin.pagin.PagingAndSortingHelper;
import com.bookstore.admin.repository.CountryRepository;
import com.bookstore.admin.repository.CustomerRepository;
import com.bookstore.common.entity.Country;
import com.bookstore.common.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    public static final int CUSTOMER_PER_PAGE = 10;

    private final CustomerRepository customerRepo;
    private final CountryRepository countryRepo;
    private final PasswordEncoder passwordEncoder;

    public void listByPage(int pageNum, PagingAndSortingHelper helper){
        helper.listEntities(pageNum, CUSTOMER_PER_PAGE, customerRepo);
    }

    public void updateCustomerEnabledStatus(Integer id, boolean enabled){
        customerRepo.updateEnabledStatus(id, enabled);
    }

    public Customer get(Integer id) throws CustomerNotFoundException{
        try{
            return customerRepo.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new CustomerNotFoundException("Could not find any customers with ID " + id);
        }
    }

    public List<Country> listAllCountries(){
        return countryRepo.findAllByOrderByNameAsc();
    }

    public boolean isEmailUnique(Integer id, String email){
        Customer existCustomer = customerRepo.findByEmail(email);

        if (existCustomer != null && existCustomer.getId() != id){
            return false;
        }
        return true;
    }

    public void save(Customer customerInForm){
        Customer customerInDB = customerRepo.findById(customerInForm.getId()).get();
        if(!customerInForm.getPassword().isEmpty()){
            String encodePassword = passwordEncoder.encode(customerInForm.getPassword());
            customerInForm.setPassword(encodePassword);
        } else {

            customerInDB.setPassword(customerInDB.getPassword());
        }

        customerInForm.setEnabled(customerInDB.isEnabled());
        customerInForm.setCreatedTime(customerInDB.getCreatedTime());
        customerInForm.setVerificationCode(customerInDB.getVerificationCode());
        customerRepo.save(customerInForm);
    }

    public void delete(Integer id) throws CustomerNotFoundException{
        Long count = customerRepo.countById(id);
        if(count == null || count == 0){
            throw new CustomerNotFoundException("Could not find any customers with ID " + id);
        }
        customerRepo.deleteById(id);
    }
}
