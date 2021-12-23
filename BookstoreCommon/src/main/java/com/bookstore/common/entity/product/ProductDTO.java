package com.bookstore.common.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private String name;
    private float price;
    private float cost;
}
