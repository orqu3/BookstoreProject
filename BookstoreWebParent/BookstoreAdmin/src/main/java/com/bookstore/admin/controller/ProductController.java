package com.bookstore.admin.controller;

import com.bookstore.admin.service.CategoryService;
import com.bookstore.admin.service.ProductService;
import com.bookstore.common.entity.Category;
import com.bookstore.common.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    public String listAll(Model model) {
        List<Product> products = productService.listAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/new")
    public String newProduct(Model model) {
        List<Category> categories = categoryService.listAll();
        Product product = new Product();
        product.setEnabled(true);
        product.setInStock(true);

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("pageTitle", "Create New Product");

        return "product_form";
    }

    @PostMapping("/products/save")
    public String saveProduct(Product product) {
        return "redirect:/products";
    }
}
