package com.bookstore.shopping.service;

import com.bookstore.common.entity.Address;
import com.bookstore.common.entity.Customer;
import com.bookstore.shopping.repository.AddressRepository;
import com.bookstore.shopping.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional
public class AddressService {

    private final AddressRepository repo;



    public AddressService(AddressRepository repo) {
        this.repo = repo;
    }

    public List<Address> listAddressBook(Customer customer) {
        return repo.findByCustomer(customer);
    }

    public void save(Address address) {
        repo.save(address);
    }

    public Address get(Integer addressId, Integer customerId) {
        return repo.findByIdAndCustomer(addressId,customerId);
    }

    public void delete(Integer addressId, Integer customerId) {
        repo.deleteByIdAndCustomer(addressId, customerId);
    }


}
