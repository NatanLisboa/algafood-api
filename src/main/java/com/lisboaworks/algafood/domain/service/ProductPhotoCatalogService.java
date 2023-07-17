package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.model.ProductPhoto;
import com.lisboaworks.algafood.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProductPhotoCatalogService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductPhoto save(ProductPhoto productPhoto) {
        return productRepository.save(productPhoto);
    }

}
