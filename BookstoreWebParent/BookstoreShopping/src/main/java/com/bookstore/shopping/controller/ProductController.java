package com.bookstore.shopping.controller;

import com.bookstore.common.entity.Category;

import com.bookstore.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.common.entity.Product;
import com.bookstore.common.exception.CategoryNotFoundException;
import com.bookstore.common.exception.ProductNotFoundException;
import com.bookstore.shopping.service.CategoryService;
import com.bookstore.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/c/{category_alias}")
    public String viewCategory (@PathVariable("category_alias") String alias, Model model) {
        Category category = categoryService.getCategory(alias);
        if (category == null) {
            return "error/404";
        }

        List<Category> listCategoryParents =  categoryService.getCategoryParents(category);

        model.addAttribute("pageTitle", category.getName());
        model.addAttribute("listCategoryParents", listCategoryParents);
        return "products_by_category";

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

        try {
                Category category = categoryService.getCategory(alias);

                List<Category> listCategoryParents = categoryService.getCategoryParents(category);

                Page<Product> pageProduct = productService.listByCategory(pageNum, category.getId());
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
                model.addAttribute("category", category);


                model.addAttribute("pageTitle", category.getName());
                model.addAttribute("listCategoryParents", listCategoryParents);
                return "product/products_by_category";

        } catch (CategoryNotFoundException ex) {
            return "error/404";
        }
    }

    @GetMapping("/p/{product_alias}")
    public String viewProductDetail(@PathVariable("product_alias") String alias, Model model) {
        try {
            Product product = productService.getProduct(alias);
            List<Category> listCategoryParents = categoryService.getCategoryParents(product.getCategory());

            model.addAttribute("listCategoryParents", listCategoryParents);
            model.addAttribute("product", product);
            model.addAttribute("pageTitle",product.getShortName());

            return "product/product_detail";
        } catch (ProductNotFoundException e) {
            return  "error/404";
        }
    }

    @GetMapping("/search")
    public  String searchFirstPage(@Param("keyword") String keyword, Model model) {
        return searchByPage(keyword,1,model);

    }

    @GetMapping("/search/page/{pageNum}")
    public String searchByPage(@Param("keyword") String keyword,
                               @PathVariable("pageNum") int pageNum,
                                Model model) {
        Page<Product> pageProduct =  productService.search(keyword,pageNum);
        List<Product> listResult = pageProduct.getContent();


        long startCount = (pageNum - 1) * ProductService.SEARCH_RESULT_PER_PAGE + 1;
        long endCount = startCount + ProductService.SEARCH_RESULT_PER_PAGE - 1;
        if (endCount > pageProduct.getTotalElements()) {
            endCount = pageProduct.getTotalElements();
        }

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", pageProduct.getTotalPages());
        model.addAttribute("startCount", startCount);
        model.addAttribute("endCount", endCount);
        model.addAttribute("totalItems", keyword + " -  Search Result");

        model.addAttribute("keyword", keyword);
        model.addAttribute("listResult", listResult);

        return "product/search_result";
    }
}
