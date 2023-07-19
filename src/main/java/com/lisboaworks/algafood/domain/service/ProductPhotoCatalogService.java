package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.model.ProductPhoto;
import com.lisboaworks.algafood.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductPhotoCatalogService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductPhoto save(ProductPhoto productPhoto) {
        Long restaurantId = productPhoto.getRestaurantId();
        Long productId = productPhoto.getProduct().getId();

        Optional<ProductPhoto> photoOptional = productRepository
                .findPhotoById(restaurantId, productId);

        photoOptional.ifPresent(productRepository::delete);

        return productRepository.save(productPhoto);
    }

}
