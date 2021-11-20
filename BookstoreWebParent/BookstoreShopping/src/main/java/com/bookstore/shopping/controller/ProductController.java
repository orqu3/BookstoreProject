package com.bookstore.shopping.controller;

import com.bookstore.common.entity.Category;
import com.bookstore.common.entity.Product;
import com.bookstore.shopping.service.CategoryService;
import com.bookstore.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/c/{category_alias}")
    public String viewCategoryFirstPage(@PathVariable("category_alias") String alias,
                                      Model model) {
        return viewCategoryByPage(alias,1,model);


    }




    @GetMapping("/c/{category_alias}/page/{pageNum}")
    public String viewCategoryByPage (@PathVariable("category_alias") String alias,
                                @PathVariable("pageNum") int pageNum,
                                Model model) {
        Category category = categoryService.getCategory(alias);
        if (category == null) {
            return "error/404";
        }

        List<Category> listCategoryParents =  categoryService.getCategoryParents(category);

        Page<Product> pageProduct = productService.listByCategory(pageNum,category.getId());
        List<Product> listProducts = pageProduct.getContent();

        long startCount = (pageNum - 1) * ProductService.PRODUCTS_PER_PAGE + 1;
        long endCount = startCount + ProductService.PRODUCTS_PER_PAGE - 1;
        if (endCount > pageProduct.getTotalElements()) {
            endCount = pageProduct.getTotalElements();
        }

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", pageProduct.getTotalPages());
        model.addAttribute("startCount", startCount);
        model.addAttribute("endCount", endCount);
        model.addAttribute("totalItems", pageProduct.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("category",category);


        model.addAttribute("pageTitle", category.getName());
        model.addAttribute("listCategoryParents", listCategoryParents);
        return "products_by_category";
    }
}
