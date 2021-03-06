package com.bookstore.shopping.service;

import com.bookstore.common.entity.CartItem;
import com.bookstore.common.entity.Customer;
import com.bookstore.common.entity.product.Product;
import com.bookstore.shopping.exception.ShoppingCartException;
import com.bookstore.shopping.repository.CartItemRepository;
import com.bookstore.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ShoppingCartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public Integer addProduct(Integer productId, Integer quantity, Customer customer) throws ShoppingCartException {
        Integer updatedQuantity = quantity;
        Product product = new Product(productId);

        CartItem cartItem = cartItemRepository.findByCustomerAndProduct(customer, product);

        if (cartItem != null) {
            updatedQuantity = cartItem.getQuantity() + quantity;

            if (updatedQuantity > 5) {
                throw new ShoppingCartException("Could not add more " + quantity + " item(s), because there's already " +
                        cartItem.getQuantity() + " item(s) in your shopping cart. Maximum allowed quantity is 5.");
            }

        } else {
            cartItem = new CartItem();
            cartItem.setCustomer(customer);
            cartItem.setProduct(product);
        }
        cartItem.setQuantity(updatedQuantity);

        cartItemRepository.save(cartItem);

        return updatedQuantity;
    }

    public List<CartItem> listCartItems(Customer customer) {
        return cartItemRepository.findByCustomer(customer);
    }

    public float updateQuantity(Integer productId, Integer quantity, Customer customer) {
        cartItemRepository.updateQuantity(quantity, customer.getId(), productId);
        Product product = productRepository.findById(productId).get();
        float subtotal = product.getDiscountPrice() * quantity;
        return subtotal;
    }

    public void removeProduct(Integer productId, Customer customer) {
        cartItemRepository.deleteByCustomerAndProduct(customer.getId(), productId);
    }

    public void deleteByCustomer(Customer customer) {
        cartItemRepository.deleteByCustomer(customer.getId());
    }
}
