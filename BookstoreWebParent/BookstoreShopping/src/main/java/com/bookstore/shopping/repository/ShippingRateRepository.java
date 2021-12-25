package com.bookstore.shopping.repository;

import com.bookstore.common.entity.Country;
import com.bookstore.common.entity.ShippingRate;
import org.springframework.data.repository.CrudRepository;

public interface ShippingRateRepository extends CrudRepository<ShippingRate, Integer> {

    ShippingRate findByCountryAndState(Country country, String state);
}
