package com.bookstore.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponseDTO {

    private Integer orderId;
    private String status;

}
