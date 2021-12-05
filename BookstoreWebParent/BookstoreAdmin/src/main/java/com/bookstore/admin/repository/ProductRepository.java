package com.bookstore.admin.repository;

import com.bookstore.common.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    Product findByName(String name);

    @Query("UPDATE Product p SET p.enabled = ?2 WHERE p.id = ?1")
    @Modifying
    void updateEnabledStatus(Integer id, Boolean enabled);

    Long countById(Integer id);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1% OR p.description LIKE %?1% OR p.category.name LIKE %?1% OR p.author LIKE %?1%")
    Page<Product> findAll(String keyword, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.id = ?1 OR p.category.allParentIDs LIKE %?2%")
    Page<Product> findAllInCategory(Integer categoryId, String categoryIdMatch, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE (p.category.id = ?1 OR p.category.allParentIDs LIKE %?2%) AND (p.name LIKE %?3% " +
            "OR p.description LIKE %?3% OR p.category.name LIKE %?3% OR p.author LIKE %?3%)")
    Page<Product> searchInCategory(Integer categoryId, String categoryIdMatch, String keyword, Pageable pageable);
}