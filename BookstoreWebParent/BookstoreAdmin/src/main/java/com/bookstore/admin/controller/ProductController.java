package com.bookstore.admin.controller;

import com.bookstore.admin.service.ProductService;
import com.bookstore.common.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public String listAll(Model model) {
        List<Product> products = productService.listAll();
        model.addAttribute("products", products);
        return "products";
    }


}
