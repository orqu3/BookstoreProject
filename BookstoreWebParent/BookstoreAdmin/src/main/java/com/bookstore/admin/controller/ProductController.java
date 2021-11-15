package com.bookstore.admin.controller;

import com.bookstore.admin.exception.ProductNotFoundException;
import com.bookstore.admin.service.CategoryService;
import com.bookstore.admin.service.ProductService;
import com.bookstore.common.entity.Category;
import com.bookstore.common.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.StyledEditorKit;
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
    public String saveProduct(Product product, RedirectAttributes redirectAttributes,
                              @RequestParam(name = "detailNames", required = false) String[] detailNames,
                              @RequestParam(name = "detailValues", required = false) String[] detailValues) {
        setProductDetails(detailNames, detailValues, product);
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "The product has been saved successfully.");
        return "redirect:/products";
    }

    private void setProductDetails(String[] detailNames, String[] detailValues, Product product) {
        if (detailNames == null || detailNames.length == 0) return;

        for (int count = 0; count < detailNames.length; count++) {
            String name = detailNames[count];
            String value = detailValues[count];

            if (!name.isEmpty() && !value.isEmpty()) {
                product.addDetail(name, value);
            }
        }
    }

    @GetMapping("/products/{id}/enabled/{status}")
    public String updateProductEnabledStatus(@PathVariable("id") Integer id,
                                             @PathVariable("status") Boolean enabled,
                                             RedirectAttributes redirectAttributes) {
        productService.updateProductEnabledStatus(id, enabled);
        String status = enabled ? "enabled" : "disabled";
        String message = "The Product ID " + id + " has been " + status;
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Integer id,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        try {
            productService.delete(id);
            redirectAttributes.addFlashAttribute("message", "The product ID " + id + " has been deleted successfully");
        } catch (ProductNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/products";
    }
}
