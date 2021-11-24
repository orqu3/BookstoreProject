package com.bookstore.shopping.repository;

import com.bookstore.common.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testFindByAlias() {
        String alias = "being-britney-pieces-of-a-modern-icon";
        Product product = productRepository.findByAlias(alias);
        assertThat(product).isNotNull();
    }
}
