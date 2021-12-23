package com.bookstore.shopping.paypal;

import com.bookstore.shopping.util.checkout.paypal.PayPalOrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


public class PayPalApiTests {

    private static final String BASE_URL = "https://api.sandbox.paypal.com";
    private static final String GET_ORDER_API = "/v2/checkout/orders/";
    private static final String CLIENT_ID = "Aa9J3FrHxdDPV8EowjMS3hXkjX3j77P5r7u80HJSfjETwtzEiAyS5lTVEYkuOVa5ERGXiQAhmWiYxvZN";
    private static final String CLIENT_SECRET = "ECuRJPya6rq3rrTKfLwVuO1XT88gW-i1RZ4get7c7IPI4lg_6TnILfNql9KHrIO8IeNRQ_wOoWiJX_I4";

    @Test
    public void testGetOrderDetails() {
        String orderId = "123Abc";
        String requestURL = BASE_URL + GET_ORDER_API + orderId;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Accept-Language", "en_US");
        headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PayPalOrderResponse> response = restTemplate.exchange(requestURL, HttpMethod.GET, request,
                PayPalOrderResponse.class);

        PayPalOrderResponse orderResponse = response.getBody();

        System.out.println("Order ID: " + orderResponse.getId());
        System.out.println("Validated: " + orderResponse.validate(orderId));
    }
}
