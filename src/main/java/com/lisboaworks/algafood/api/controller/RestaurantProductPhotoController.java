package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.ProductPhotoDTOAssembler;
import com.lisboaworks.algafood.api.dto.ProductPhotoDTO;
import com.lisboaworks.algafood.api.dto.input.ProductPhotoInput;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import com.lisboaworks.algafood.domain.model.Product;
import com.lisboaworks.algafood.domain.model.ProductPhoto;
import com.lisboaworks.algafood.domain.service.PhotoStorageService;
import com.lisboaworks.algafood.domain.service.ProductPhotoCatalogService;
import com.lisboaworks.algafood.domain.service.ProductRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
@AllArgsConstructor
public class RestaurantProductPhotoController {

    private final ProductRegisterService productRegisterService;
    private final ProductPhotoCatalogService productPhotoCatalogService;
    private final ProductPhotoDTOAssembler productPhotoDTOAssembler;
    private final PhotoStorageService photoStorageService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductPhotoDTO getPhoto(@PathVariable Long restaurantId, @PathVariable Long productId) {
        ProductPhoto productPhoto = productPhotoCatalogService.findOrThrowException(restaurantId, productId);
        return productPhotoDTOAssembler.toDTO(productPhoto);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> servePhoto(@PathVariable Long restaurantId, @PathVariable Long productId) {
        try {
            ProductPhoto productPhoto = productPhotoCatalogService.findOrThrowException(restaurantId, productId);
            InputStream inputStream = photoStorageService.get(productPhoto.getFilename());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(inputStream));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoDTO updatePhoto(@PathVariable Long restaurantId,
                                       @PathVariable Long productId, @Valid ProductPhotoInput productPhotoInput) throws IOException {
        Product product = productRegisterService.findOrThrowException(restaurantId, productId);

        MultipartFile file = productPhotoInput.getFile();

        ProductPhoto productPhoto = new ProductPhoto();
        productPhoto.setProduct(product);
        productPhoto.setDescription(productPhotoInput.getDescription());
        productPhoto.setContentType(file.getContentType());
        productPhoto.setSize(file.getSize());
        productPhoto.setFilename(file.getOriginalFilename());

        ProductPhoto savedPhoto = productPhotoCatalogService.save(productPhoto, file.getInputStream());

        return productPhotoDTOAssembler.toDTO(savedPhoto);
    }

}
