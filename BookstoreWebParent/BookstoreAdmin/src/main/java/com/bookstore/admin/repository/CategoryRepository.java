package com.bookstore.admin.repository;

import com.bookstore.common.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE CONCAT(c.id, ' ', c.name, ' ', c.alias) LIKE %?1%")
    Page<Category> findAll(String keyword, Pageable pageable);

    Long countById(Integer id);

    @Query("UPDATE Category c SET c.enabled = ?2 WHERE c.id = ?1")
    @Modifying
    void updateEnabledStatus(Integer id, Boolean enabled);

    @Query("SELECT c FROM Category c WHERE c.parent.id is NULL")
    List<Category> findRootCategories();

}
