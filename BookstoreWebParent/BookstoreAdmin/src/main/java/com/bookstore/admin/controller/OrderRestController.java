package com.bookstore.admin.controller;

import com.bookstore.admin.service.OrderService;
import com.bookstore.common.dto.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService service;

    @PostMapping("/orders_shipper/update/{id}/{status}")
    public OrderResponseDTO updateOrderStatus(@PathVariable("id") Integer orderId,
                                              @PathVariable("status") String status) {
        service.updateStatus(orderId, status);
        return new OrderResponseDTO(orderId, status);
    }
}
