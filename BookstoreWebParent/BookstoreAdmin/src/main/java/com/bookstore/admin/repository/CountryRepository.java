package com.bookstore.admin.repository;

import com.bookstore.common.entity.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Integer> {

    List<Country> findAllByOrderByNameAsc();
}
