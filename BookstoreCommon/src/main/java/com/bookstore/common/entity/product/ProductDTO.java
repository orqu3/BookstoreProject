package com.bookstore.common.entity.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private float price;
    private float cost;

    public ProductDTO(String name, float price, float cost) {
        this.name = name;
        this.price = price;
        this.cost = cost;
    }
}
