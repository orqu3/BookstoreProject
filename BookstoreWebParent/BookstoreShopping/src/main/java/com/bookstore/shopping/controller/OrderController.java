package com.bookstore.shopping.controller;


import com.bookstore.common.entity.Customer;
import com.bookstore.common.entity.order.Order;
import com.bookstore.shopping.service.CustomerService;
import com.bookstore.shopping.service.OrderService;
import com.bookstore.shopping.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {
    private final OrderService orderService;

    private final CustomerService customerService;

    public OrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @GetMapping("/orders")
    public String listFirstPage(Model model, HttpServletRequest request) {
        return listOrderByPage(model, request, 1, "orderTime", "desc", null);
    }

    @GetMapping("/orders/page/{pageNum}")
    public String listOrderByPage(Model model, HttpServletRequest request,
                                  @PathVariable(name = "pageNum") int pageNum,
                                  String sortField, String sortDir, String orderKeyword) {
        Customer customer = getAuthenticatedCustomer(request);

        Page<Order> page = orderService.listForCustomerByPage(customer,pageNum,sortField,sortDir,orderKeyword);
        List<Order> listOrders = page.getContent();

        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("listOrders", listOrders);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("orderKeyword", orderKeyword);
        model.addAttribute("moduleURL", "/orders");
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        long startCount = (pageNum - 1) * OrderService.ORDER_PER_PAGE + 1;
        model.addAttribute("startCount", startCount);

        long endCount = startCount + OrderService.ORDER_PER_PAGE - 1;
        if(endCount > page.getTotalElements()) {
            endCount = page.getTotalElements();
        }

        model.addAttribute("endCount", endCount);
        return "orders/orders_customer";

    }

    private Customer getAuthenticatedCustomer (HttpServletRequest request) {
        String email = Utility.getEmailOfAuthenticatedCustomer(request);
        return customerService.getCustomerByEmail(email);
    }
}
