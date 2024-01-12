package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.ProductPhotoModel;
import com.lisboaworks.algafood.api.v1.model.input.ProductPhotoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SecurityRequirement(name = "security_auth")
public interface RestaurantProductPhotoControllerOpenApi {

    @Operation(summary = "Get the restaurant product photo", responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductPhotoModel.class)),
                    @Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")),
                    @Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary"))
            })
    })
    ProductPhotoModel getPhoto(Long restaurantId, Long productId);

    @Operation(summary = "Update restaurant product photo")
    ProductPhotoModel updatePhoto(@Parameter(description = "Restaurant id", example = "1", required = true) Long restaurantId,
                                  @Parameter(description = "Product id", example = "1", required = true) Long productId,
                                  @RequestBody(required = true) ProductPhotoInput productPhotoInput) throws IOException;

    @Operation(hidden = true)
    ResponseEntity<InputStreamResource> servePhoto(Long restaurantId, Long productId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;

    void deletePhoto(Long restaurantId, Long productId);

}
