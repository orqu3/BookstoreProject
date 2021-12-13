package com.bookstore.common.entity.order;

import com.bookstore.common.entity.IdBasedEntity;
import com.bookstore.common.entity.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@Getter
@Setter
public class OrderDetail extends IdBasedEntity {

    private int quantity;
    private float productCost;
    private float shippingCost;
    private float unitPrice;
    private float subtotal;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
