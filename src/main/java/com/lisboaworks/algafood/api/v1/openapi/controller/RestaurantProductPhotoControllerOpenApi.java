package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.ProductPhotoModel;
import com.lisboaworks.algafood.api.v1.model.input.ProductPhotoInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SecurityRequirement(name = "security_auth")
public interface RestaurantProductPhotoControllerOpenApi {

    ProductPhotoModel getPhoto(Long restaurantId, Long productId);

    ProductPhotoModel updatePhoto(Long restaurantId,
                                  Long productId,
                                  ProductPhotoInput productPhotoInput,
                                  MultipartFile file) throws IOException;

    ResponseEntity<InputStreamResource> servePhoto(Long restaurantId, Long productId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;

    void deletePhoto(Long restaurantId, Long productId);

}
