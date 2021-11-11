package com.bookstore.admin.repository;

import com.bookstore.common.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

    Long countById(Integer id);

    @Query("UPDATE Category c SET c.enabled = ?2 WHERE c.id = ?1")
    @Modifying
    void updateEnabledStatus(Integer id, Boolean enabled);

    @Query("SELECT c FROM Category c WHERE c.parent.id is NULL")
    List<Category> findRootCategories(Sort sort);

    @Query("SELECT c FROM Category c WHERE c.parent.id is NULL")
    Page<Category> findRootCategories(Pageable pageable);

    Category findByName(String name);

    Category findByAlias(String alias);

    @Query("SELECT c FROM Category c WHERE c.name LIKE %?1%")
    Page<Category> search(String keyword, Pageable pageable);

    @Query("SELECT NEW Category(c.id, c.name) FROM Category c ORDER BY c.name ASC")
    List<Category> findAll();
}
