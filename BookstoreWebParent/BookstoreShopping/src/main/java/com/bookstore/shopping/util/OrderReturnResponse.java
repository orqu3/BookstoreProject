package com.bookstore.shopping.util;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderReturnResponse {
    private Integer orderId;

    public OrderReturnResponse(Integer orderId) {
    }
}
