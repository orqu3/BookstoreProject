package com.bookstore.admin.util.pagination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface SearchRepository<T, ID> extends PagingAndSortingRepository<T, ID> {
    Page<T> findAll(String keyword, Pageable pageable);
    Page<T> findAll(Pageable pageable);

}
