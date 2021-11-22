package com.bookstore.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.bookstore.common.entity","com.bookstore.shopping.repository"})
public class BookstoreShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreShoppingApplication.class, args);
    }

}
