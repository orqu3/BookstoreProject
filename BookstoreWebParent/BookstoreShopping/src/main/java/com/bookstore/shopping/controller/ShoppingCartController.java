package com.bookstore.shopping.controller;

import com.bookstore.common.entity.CartItem;
import com.bookstore.common.entity.Customer;
import com.bookstore.common.exception.CustomerNotFoundException;
import com.bookstore.shopping.service.CustomerService;
import com.bookstore.shopping.service.ShoppingCartService;
import com.bookstore.shopping.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService cartService;
    private final CustomerService customerService;

    @GetMapping("/cart")
    public String viewCart(Model model, HttpServletRequest request) {
        Customer customer = getAuthenticatedCustomer(request);
        List<CartItem> cartItems = cartService.listCartItems(customer);

        float estimatedTotal = 0.0F;
        for(CartItem item : cartItems) {
            estimatedTotal += item.getSubTotal();
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("estimatedTotal", estimatedTotal);

        return "cart/shopping_cart";
    }

    private Customer getAuthenticatedCustomer(HttpServletRequest request) {
        String email = Utility.getEmailOfAuthenticatedCustomer(request);
        return customerService.getCustomerByEmail(email);
    }
}
