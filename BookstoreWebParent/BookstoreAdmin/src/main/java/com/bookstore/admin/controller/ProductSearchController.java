package com.bookstore.admin.controller;

import com.bookstore.admin.service.ProductService;
import com.bookstore.common.entity.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductService service;

    @GetMapping("/orders/search_product")
    public String showSearchProductPage(){
        return "orders/search_product";
    }

    @PostMapping("/orders/search_product")
    public String searchProducts(String keyword){
        return "redirect:/orders/search_product/page/1?sortField=name&sortDir=asc&keyword=" + keyword;
    }

    @GetMapping("/orders/search_product/page/{pageNum}")
    public String searchProductsByPage(@PathVariable(name = "pageNum") int pageNum,
                                       Model model,
                                       @Param("sortField") String sortField,
                                       @Param("sortDir") String sortDir,
                                       @Param("keyword") String keyword){
        Page<Product> page = service.searchProducts(pageNum, sortField, sortDir, keyword);
        List<Product> listProducts = page.getContent();

        long startCount = (pageNum - 1) * service.PRODUCTS_PER_PAGE + 1;
        model.addAttribute("startCount", startCount);

        long endCount = startCount + service.PRODUCTS_PER_PAGE - 1;

        if (endCount > page.getTotalElements()) {
            endCount = page.getTotalElements();
        }

        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("keyword", keyword);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("endCount", endCount);

        return "orders/search_product";
    }
}
