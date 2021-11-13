package com.bookstore.shopping.category;

import com.bookstore.common.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
public interface CategoryRepository extends CrudRepository <Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.enabled = true ORDER BY c.name ASC")
    public List<Category> findAllEnable();
}
