package com.bookstore.admin.repository;

import com.bookstore.common.entity.Country;
import com.bookstore.common.entity.State;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StateRepository extends CrudRepository<State, Integer> {
    List<State> findByCountryOrderByNameAsc(Country country);
}
