package com.bookstore.admin.service;

import com.bookstore.admin.exception.ShippingRateNotFoundException;
import com.bookstore.admin.repository.ProductRepository;
import com.bookstore.admin.repository.ShippingRateRepository;
import com.bookstore.common.entity.ShippingRate;
import com.bookstore.common.entity.product.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ShippingRateServiceTests {

    @MockBean
    private ShippingRateRepository shippingRateRepository;

    @MockBean
    private ProductRepository productRepository;

    @InjectMocks
    private ShippingRateService shipService;

    /**
     * Для прохождения тестов в классе ShippingRateService необходимо модификаторы private final заменить аннотацией @Autowired
     * */

    @Test
    public void testCalculateShippingCost_NotRateFound(){
        Integer productId = 1;
        Integer countryId = 234;
        String state = "ABCDE";

        Mockito.when(shippingRateRepository.findByCountryAndState(countryId, state)).thenReturn(null);

        assertThrows(ShippingRateNotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                shipService.calculateShippingCost(productId, countryId, state);
            }
        });
    }

    @Test
    public void testCalculateShippingCost_RateFound() throws ShippingRateNotFoundException {
        Integer productId = 1;
        Integer countryId = 234;
        String state = "New York";

        ShippingRate shippingRate = new ShippingRate();
        shippingRate.setRate(10);

        Mockito.when(shippingRateRepository.findByCountryAndState(countryId, state)).thenReturn(shippingRate);

        Product product = new Product();
        product.setWeight(5);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        float shippingCost = shipService.calculateShippingCost(productId, countryId, state);

        assertEquals(50, shippingCost);
    }

}
