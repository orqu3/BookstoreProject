package com.bookstore.shopping.repository;

import com.bookstore.common.entity.Country;
import com.bookstore.common.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateCustomer1() {
        Integer countryId = 234;
        Country country = entityManager.find(Country.class, countryId);

        Customer customer = new Customer();
        customer.setCountry(country);
        customer.setFirstName("David");
        customer.setLastName("Fountaine");
        customer.setPassword("password123");
        customer.setEmail("david.s.fountaine@gmail.com");
        customer.setPhoneNumber("312-462-7518");
        customer.setAddressLine1("1927 West Drive");
        customer.setCity("Sacramento");
        customer.setState("California");
        customer.setPostalCode("95867");
        customer.setCreatedTime(new Date());
        customer.setEnabled(true);
        customer.setVerificationCode("code_123");

        Customer savedCustomer = customerRepository.save(customer);
        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isGreaterThan(0);
    }

    @Test
    public void testListCustomers() {
        Iterable<Customer> customers = customerRepository.findAll();
        customers.forEach(System.out::println);

        assertThat(customers).hasSizeGreaterThan(0);
    }

    @Test
    public void testUpdateCustomer() {
        Integer customerId = 1;
        String lastName = "Stanfield";

        Customer customer = customerRepository.findById(customerId).get();
        customer.setLastName(lastName);
        customer.setEnabled(false);

        Customer updatedCustomer = customerRepository.save(customer);
        assertThat(updatedCustomer.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void testGetCustomer() {
        Integer customerId = 1;
        Optional<Customer> findById = customerRepository.findById(customerId);

        assertThat(findById).isPresent();

        Customer customer = findById.get();
        System.out.println(customer);
    }

    @Test
    public void testFindByEmail() {
        String email = "david.s.fountaine@gmail.com";
        Customer customer = customerRepository.findByEmail(email);

        assertThat(customer).isNotNull();
        System.out.println(customer);
    }

    @Test
    public void testEnableCustomer() {
        Integer customerId = 1;
        customerRepository.enable(customerId);

        Customer customer = customerRepository.findById(customerId).get();
        assertThat(customer.getEnabled()).isTrue();
    }

    @Test
    public void testDeleteCustomer() {
        Integer customerId = 1;
        customerRepository.deleteById(customerId);

        Optional<Customer> findById = customerRepository.findById(customerId);
        assertThat(findById).isNotPresent();
    }

    @Test
    public void testFindByVerificationCode() {
        String code = "code_123";
        Customer customer = customerRepository.findByVerificationCode(code);

        assertThat(customer).isNotNull();
        System.out.println(customer);
    }
}
