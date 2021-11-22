package com.bookstore.shopping.service;


import com.bookstore.common.entity.Product;
import com.bookstore.common.exception.ProductNotFoundException;
import com.bookstore.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public static final int PRODUCTS_PER_PAGE = 10;
    public static final int SEARCH_RESULT_PER_PAGE = 10;


    @Autowired
    private ProductRepository repo;

    public Page<Product> listByCategory (int pageNum, Integer categoryId) {
        String categoryIdMatch = "-" + String.valueOf(categoryId) + "-";
        Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);

        return repo.listByCategory(categoryId,categoryIdMatch,pageable);
    }

    public Product getProduct(String alias) throws ProductNotFoundException{
        Product product = repo.findByAlias(alias);
        if (product == null) {
            throw new ProductNotFoundException("Could not find any products with alias" + alias);
        }
        return product;
    }

    public Page<Product> search(String keyword, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum - 1, SEARCH_RESULT_PER_PAGE);
        return repo.search(keyword, pageable);


    }


}
