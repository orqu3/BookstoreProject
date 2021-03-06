package com.bookstore.common.entity;

import com.bookstore.common.entity.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @Transient
    private float shippingCost;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", customer=" + customer.getFullName() +
                ", product=" + product.getName() +
                ", quantity=" + quantity +
                '}';
    }

    @Transient
    public float getSubTotal() {
        return product.getDiscountPrice() * quantity;
    }

    @Transient
    public float getShippingCost() {
        return shippingCost;
    }
}
