package com.bookstore.admin.service;

import com.bookstore.admin.exception.CategoryNotFoundException;
import com.bookstore.admin.repository.CategoryRepository;
import com.bookstore.common.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    public static final int CATEGORIES_PER_PAGE = 10;

    private final CategoryRepository categoryRepository;

    public List<Category> listAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Page<Category> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, CATEGORIES_PER_PAGE, sort);

        if (keyword != null) {
            return categoryRepository.findAll(keyword, pageable);
        }
        return categoryRepository.findAll(pageable);
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category get(Integer id) throws CategoryNotFoundException {
        try {
            return categoryRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new CategoryNotFoundException("Could not find any category with id " + id);
        }
    }

    public void delete(Integer id) throws CategoryNotFoundException {
        Long countById = categoryRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new CategoryNotFoundException("Could not find any category with ID " + id);
        }
        categoryRepository.deleteById(id);
    }

    public void updateCategoryEnabledStatus(Integer id, Boolean enabled) {
        categoryRepository.updateEnabledStatus(id, enabled);
    }
}

