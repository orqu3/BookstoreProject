package com.bookstore.shopping.repository;

import com.bookstore.common.entity.AuthenticationType;
import com.bookstore.common.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testUpdateAuthenticationType() {
        Integer id = 1;
        customerRepository.updateAuthenticationType(id, AuthenticationType.FACEBOOK);

        Customer customer = customerRepository.findById(id).get();
        assertThat(customer.getAuthenticationType()).isEqualTo(AuthenticationType.FACEBOOK);
    }
}
