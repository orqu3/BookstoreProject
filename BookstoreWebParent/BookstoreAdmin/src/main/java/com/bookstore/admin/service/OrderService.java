package com.bookstore.admin.service;

import com.bookstore.admin.repository.OrderRepository;
import com.bookstore.common.entity.Customer;
import com.bookstore.common.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    public static final int ORDERS_PER_PAGE = 10;

    private final OrderRepository repo;

    public Page<Order> listByPage(int pageNum, String sortField, String sortDir, String keyword){

        Sort sort = null;

        if("destination".equals(sortField)){
            sort = Sort.by("country").and(Sort.by("state").and(Sort.by("city")));
        } else {
            sort = Sort.by(sortField);
        }

        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, ORDERS_PER_PAGE, sort);

        if (keyword != null) {
            return repo.findAll(keyword, pageable);
        }

        return repo.findAll(pageable);
    }



}
