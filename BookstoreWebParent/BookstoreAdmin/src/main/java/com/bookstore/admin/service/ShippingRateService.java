package com.bookstore.admin.service;

import com.bookstore.admin.exception.ShippingRateAlreadyExistsException;
import com.bookstore.admin.exception.ShippingRateNotFoundException;
import com.bookstore.admin.repository.CountryRepository;
import com.bookstore.admin.repository.ProductRepository;
import com.bookstore.admin.repository.ShippingRateRepository;
import com.bookstore.common.entity.Country;
import com.bookstore.common.entity.ShippingRate;
import com.bookstore.common.entity.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ShippingRateService {

    public static final int RATES_PER_PAGE = 10;

    private final ShippingRateRepository shippingRateRepository;
    private final CountryRepository countryRepository;
    private final ProductRepository productRepository;

    public Page<ShippingRate> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, RATES_PER_PAGE, sort);

        if (keyword != null) {
            return shippingRateRepository.findAll(keyword, pageable);
        }
        return shippingRateRepository.findAll(pageable);
    }

    public List<Country> listAllCountries() {
        return countryRepository.findAllByOrderByNameAsc();
    }

    public void save(ShippingRate rateInForm) throws ShippingRateAlreadyExistsException {
        ShippingRate rateInDB = shippingRateRepository.findByCountryAndState(rateInForm.getCountry().getId(), rateInForm.getState());

        boolean foundExistingRateInNewMode = rateInForm.getId() == null && rateInDB != null;
        boolean foundDifferentExistingRateInEditMode = rateInForm.getId() != null && rateInDB != null;

        if (foundExistingRateInNewMode || foundDifferentExistingRateInEditMode) {
            throw new ShippingRateAlreadyExistsException("There's already a rate for the destination: "
                    + rateInForm.getCountry().getName() + ", " + rateInForm.getState());
        }
        shippingRateRepository.save(rateInForm);
    }

    public ShippingRate get(Integer id) throws ShippingRateNotFoundException {
        try {
            return shippingRateRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new ShippingRateNotFoundException("Could not find shipping rate with ID " + id);
        }
    }

    public void updateCODSupport(Integer id, Boolean codSupported) throws ShippingRateNotFoundException {
        Long count = shippingRateRepository.countById(id);
        if (count == null || count == 0) {
            throw new ShippingRateNotFoundException("Could not find shipping rate with ID " + id);
        }
        shippingRateRepository.updateCODSupport(id, codSupported);
    }

    public void delete(Integer id) throws ShippingRateNotFoundException {
        Long count = shippingRateRepository.countById(id);
        if (count == null || count == 0) {
            throw new ShippingRateNotFoundException("Could not find shipping rate with ID " + id);
        }
        shippingRateRepository.deleteById(id);
    }

    public float calculateShippingCost(Integer productId, Integer countryId, String state) throws ShippingRateNotFoundException {
        ShippingRate shippingRate = shippingRateRepository.findByCountryAndState(countryId, state);

        if (shippingRate == null) {
            throw new ShippingRateNotFoundException("No shipping rate found for the given destination. You have to " +
                    "enter shipping cost manually");
        }

        Product product = productRepository.findById(productId).get();

        float weight = product.getWeight();

        return weight * shippingRate.getRate();
    }
}
