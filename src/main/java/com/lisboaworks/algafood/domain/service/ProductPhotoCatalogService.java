package com.lisboaworks.algafood.domain.service;

import static com.lisboaworks.algafood.domain.service.PhotoStorageService.NewPhoto;

import com.lisboaworks.algafood.api.dto.ProductPhotoDTO;
import com.lisboaworks.algafood.domain.exception.ProductPhotoNotFoundException;
import com.lisboaworks.algafood.domain.model.ProductPhoto;
import com.lisboaworks.algafood.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductPhotoCatalogService {

    private final ProductRepository productRepository;
    private final PhotoStorageService photoStorageService;

    @Transactional
    public ProductPhoto save(ProductPhoto productPhoto, InputStream fileData) {
        Long restaurantId = productPhoto.getRestaurantId();
        Long productId = productPhoto.getProduct().getId();
        String newFilename = photoStorageService.generateNewFilename(productPhoto.getFilename());
        String existentFilename = null;

        Optional<ProductPhoto> photoOptional = productRepository
                .findPhotoById(restaurantId, productId);

        if (photoOptional.isPresent()) {
            existentFilename = photoOptional.get().getFilename();
            productRepository.delete(photoOptional.get());
        }

        productPhoto.setFilename(newFilename);
        productPhoto = productRepository.save(productPhoto);
        productRepository.flush();

        NewPhoto newPhoto = NewPhoto.builder()
                .filename(productPhoto.getFilename())
                .inputStream(fileData)
                .build();

        photoStorageService.replace(existentFilename, newPhoto);

        return productPhoto;
    }

    public ProductPhoto findOrThrowException(Long restaurantId, Long productId) {
        return productRepository.findPhotoById(restaurantId, productId)
                .orElseThrow(() -> new ProductPhotoNotFoundException(restaurantId, productId));
    }
}
