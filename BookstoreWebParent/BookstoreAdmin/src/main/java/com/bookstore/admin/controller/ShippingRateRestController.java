package com.bookstore.admin.controller;

import com.bookstore.admin.exception.ShippingRateNotFoundException;
import com.bookstore.admin.service.ShippingRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShippingRateRestController {

    private final ShippingRateService shippingRateService;

    @PostMapping("/get_shipping_cost")
    public String getShippingCost(Integer productId, Integer countryId, String state) throws ShippingRateNotFoundException {
        float shippingCost = shippingRateService.calculateShippingCost(productId, countryId, state);
        return String.valueOf(shippingCost);
    }
}
