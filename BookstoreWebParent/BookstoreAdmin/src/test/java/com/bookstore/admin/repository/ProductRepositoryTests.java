package com.bookstore.admin.repository;

import com.bookstore.common.entity.Category;
import com.bookstore.common.entity.product.Product;
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
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateFirstProduct() {
        Category category = entityManager.find(Category.class, 12);

        Product product = new Product();
        product.setName("Doctor Zhivago");
        product.setAlias("doctor_zhivago");
        product.setAuthor("Boris Pasternak");
        product.setDescription("Dr. Yury Zhivago, Pasternak’s alter ego, is a poet, philosopher, and physician whose " +
                "life is disrupted by the war and by his love for Lara, the wife of a revolutionary. His artistic nature" +
                " makes him vulnerable to the brutality and harshness of the Bolsheviks; wandering throughout Russia, he" +
                " is unable to take control of his fate, and dies in utter poverty. The poems he leaves behind " +
                "constitute some of the most beautiful writing in the novel.");
        product.setCategory(category);
        product.setCost(34);
        product.setPrice(34);
        product.setCreatedTime(new Date());
        product.setUpdatedTime(new Date());
        product.setEnabled(true);
        product.setInStock(true);
        product.setWeight(0.5f);

        Product savedProduct = productRepository.save(product);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateSecondProduct() {
        Category category = entityManager.find(Category.class, 12);

        Product product = new Product();
        product.setName("The Incorrigible Optimists Club");
        product.setAlias("the_incorrigible_optimists_club");
        product.setAuthor("Jean-Michel Guenassia");
        product.setDescription("Paris, 1959. As dusk settles over the immigrant quarter, 12-year-old Michel " +
                "Marini—amateur photographer and compulsive reader—is drawn to the hum of the local bistro. From his " +
                "usual position at the football table, he has a vantage point on a grown-up world—of rock 'n' roll and " +
                "of the Algerian War. But as the sun sinks and the plastic players spin, Michel's concentration is not " +
                "on the game, but on the huddle of men gathered in the shadows of a back room. Past the bar, behind a " +
                "partly drawn curtain, a group of eastern European men gather, where under a cirrus of smoke and over " +
                "the squares of chess boards, they tell of their lives before France—of lovers and wives, children and " +
                "ambitions.");
        product.setCategory(category);
        product.setCost(30);
        product.setPrice(30);
        product.setCreatedTime(new Date());
        product.setUpdatedTime(new Date());
        product.setEnabled(true);
        product.setInStock(false);
        product.setWeight(0.5f);

        Product savedProduct = productRepository.save(product);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllProducts() {
        Iterable<Product> iterableProducts = productRepository.findAll();
        iterableProducts.forEach(System.out::println);
    }

    @Test
    public void testGetProduct() {
        Integer id = 2;
        Product product = productRepository.findById(id).get();
        System.out.println(product);
        assertThat(product).isNotNull();
    }

    @Test
    public void testUpdateProduct() {
        Integer id = 1;
        Product product = productRepository.findById(id).get();
        product.setPrice(40);
        productRepository.save(product);
        Product updatedProduct = entityManager.find(Product.class, id);
        assertThat(updatedProduct.getPrice()).isEqualTo(40);
    }

    @Test
    public void testDeleteProduct() {
        Integer id = 2;
        productRepository.deleteById(id);
        Optional<Product> result = productRepository.findById(id);
        assertThat(result.isEmpty());
    }

    @Test
    public void testSaveProductWithDetails() {
        Integer productId = 1;
        Product product = productRepository.findById(productId).get();
        product.addDetail("detail name", "detail value");
        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct.getDetails()).isNotEmpty();
    }
}
