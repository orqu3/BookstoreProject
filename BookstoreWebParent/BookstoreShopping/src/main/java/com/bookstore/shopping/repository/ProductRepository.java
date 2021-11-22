package com.bookstore.shopping.repository;

import com.bookstore.common.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository <Product, Integer> {


    @Query ("SELECT p FROM Product p WHERE p.enabled = true " + "AND (p.category.id = ?1)" + "ORDER BY p.name ASC")
    public Page<Product> listByCategory(Integer categoryId, String categoryIdMatch, Pageable pageable);

    public Product findByAlias (String alias);

    @Query(value = "SELECT * FROM products WHERE enabled = true AND "
            + "MATCH (name, descriptor) AGAINST (?1)",
            nativeQuery = true)
    public Page<Product> search(String keyword, Pageable pageable);
}
