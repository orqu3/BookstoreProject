package com.bookstore.shopping.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderReturnRequest {
    private Integer orderId;
    private String reason;
    private String note;


}
