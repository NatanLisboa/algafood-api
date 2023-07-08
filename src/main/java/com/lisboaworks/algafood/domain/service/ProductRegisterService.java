package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.ProductNotFoundException;
import com.lisboaworks.algafood.domain.model.Product;
import com.lisboaworks.algafood.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProductRegisterService {

    private final RestaurantRegisterService restaurantRegisterService;
    private final ProductRepository productRepository;

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findOrThrowException(Long restaurantId, Long productId){
        return productRepository.findById(restaurantId, productId)
                .orElseThrow(() -> new ProductNotFoundException(restaurantId, productId));
    }

}
