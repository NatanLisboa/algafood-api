package com.lisboaworks.algafood.api.v1.controller;

import com.lisboaworks.algafood.api.v1.assembler.ProductPhotoModelAssembler;
import com.lisboaworks.algafood.api.v1.model.ProductPhotoModel;
import com.lisboaworks.algafood.api.v1.model.input.ProductPhotoInput;
import com.lisboaworks.algafood.api.v1.openapi.controller.RestaurantProductPhotoControllerOpenApi;
import com.lisboaworks.algafood.core.security.CheckSecurity;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import com.lisboaworks.algafood.domain.model.Product;
import com.lisboaworks.algafood.domain.model.ProductPhoto;
import com.lisboaworks.algafood.domain.service.PhotoStorageService;
import com.lisboaworks.algafood.domain.service.PhotoStorageService.RetrievedPhoto;
import com.lisboaworks.algafood.domain.service.ProductPhotoCatalogService;
import com.lisboaworks.algafood.domain.service.ProductRegisterService;
import lombok.AllArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/restaurants/{restaurantId}/products/{productId}/photo", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RestaurantProductPhotoController implements RestaurantProductPhotoControllerOpenApi {

    private final ProductRegisterService productRegisterService;
    private final ProductPhotoCatalogService productPhotoCatalogService;
    private final ProductPhotoModelAssembler productPhotoModelAssembler;
    private final PhotoStorageService photoStorageService;

    @GetMapping
    @CheckSecurity.Restaurants.CanGet
    public ProductPhotoModel getPhoto(@PathVariable Long restaurantId, @PathVariable Long productId) {
        ProductPhoto productPhoto = productPhotoCatalogService.findOrThrowException(restaurantId, productId);
        return productPhotoModelAssembler.toModel(productPhoto);
    }

    @GetMapping(produces = MediaType.ALL_VALUE)
    public ResponseEntity<InputStreamResource> servePhoto(@PathVariable Long restaurantId,
                                                          @PathVariable Long productId, @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        try {
            ProductPhoto productPhoto = productPhotoCatalogService.findOrThrowException(restaurantId, productId);
            MediaType photoMediaType = MediaType.parseMediaType(productPhoto.getContentType());
            List<MediaType> acceptedMediaTypes = MediaType.parseMediaTypes(acceptHeader);
            this.verifyMediaTypeCompatibility(photoMediaType, acceptedMediaTypes);
            RetrievedPhoto retrievedPhoto = photoStorageService.get(productPhoto.getFilename());
            if (retrievedPhoto.hasUrl()) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, retrievedPhoto.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(photoMediaType)
                        .body(new InputStreamResource(retrievedPhoto.getInputStream()));
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CheckSecurity.Restaurants.CanManageOperation
    public ProductPhotoModel updatePhoto(@PathVariable Long restaurantId,
                                         @PathVariable Long productId,
                                         @Valid ProductPhotoInput productPhotoInput) throws IOException {
        Product product = productRegisterService.findOrThrowException(restaurantId, productId);

        MultipartFile file = productPhotoInput.getFile();

        ProductPhoto productPhoto = new ProductPhoto();
        productPhoto.setProduct(product);
        productPhoto.setDescription(productPhotoInput.getDescription());
        productPhoto.setContentType(file.getContentType());
        productPhoto.setSize(file.getSize());
        productPhoto.setFilename(file.getOriginalFilename());

        ProductPhoto savedPhoto = productPhotoCatalogService.save(productPhoto, file.getInputStream());

        return productPhotoModelAssembler.toModel(savedPhoto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurants.CanManageOperation
    public void deletePhoto(@PathVariable Long restaurantId, @PathVariable Long productId) {
        productPhotoCatalogService.delete(restaurantId, productId);
    }

    private void verifyMediaTypeCompatibility(MediaType photoMediaType, List<MediaType> acceptedMediaTypes) throws HttpMediaTypeNotAcceptableException {
        boolean isCompatible = acceptedMediaTypes.stream()
                .anyMatch(acceptedMediaType -> acceptedMediaType.isCompatibleWith(photoMediaType));

        if (!isCompatible) {
            throw new HttpMediaTypeNotAcceptableException(acceptedMediaTypes);
        }
    }

}
