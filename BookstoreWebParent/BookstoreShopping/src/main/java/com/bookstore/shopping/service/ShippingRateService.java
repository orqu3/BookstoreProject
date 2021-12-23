package com.bookstore.shopping.service;

import com.bookstore.common.entity.Address;
import com.bookstore.common.entity.Customer;
import com.bookstore.common.entity.ShippingRate;
import com.bookstore.shopping.repository.ShippingRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingRateService {

    private final ShippingRateRepository shippingRateRepository;

    public ShippingRate getShippingRateForCustomer(Customer customer) {
        String state = customer.getState();

        if (state == null || state.isEmpty()) {
            state = customer.getCity();
        }

        return shippingRateRepository.findByCountryAndState(customer.getCountry(), state);
    }

    public ShippingRate getShippingRateForAddress(Address address) {
        String state = address.getState();

        if (state == null || state.isEmpty()) {
            state = address.getCity();
        }

        return shippingRateRepository.findByCountryAndState(address.getCountry(), state);
    }

}
