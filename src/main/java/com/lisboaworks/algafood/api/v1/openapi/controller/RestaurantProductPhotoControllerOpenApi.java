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
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.io.IOException;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Products")
public interface RestaurantProductPhotoControllerOpenApi {

    @Operation(summary = "Get the restaurant product photo", responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductPhotoModel.class)),
                    @Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")),
                    @Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary"))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid restaurant id or product id",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            ),
            @ApiResponse(responseCode = "404", description = "Product photo not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ProductPhotoModel getPhoto(@Parameter(description = "Restaurant id", example = "1", required = true) Long restaurantId,
                               @Parameter(description = "Product id", example = "1", required = true) Long productId);

    @Operation(summary = "Update restaurant product photo")
    ProductPhotoModel updatePhoto(@Parameter(description = "Restaurant id", example = "1", required = true) Long restaurantId,
                                  @Parameter(description = "Product id", example = "1", required = true) Long productId,
                                  @RequestBody(required = true) ProductPhotoInput productPhotoInput) throws IOException;

    @Operation(hidden = true)
    ResponseEntity<InputStreamResource> servePhoto(Long restaurantId, Long productId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;

    @Operation(summary = "Delete restaurant product photo", responses = {
            @ApiResponse(responseCode = "204", description = "Product photo deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid restaurant id or product id",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            ),
            @ApiResponse(responseCode = "404", description = "Product photo not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    void deletePhoto(@Parameter(description = "Restaurant id", example = "1", required = true) Long restaurantId,
                     @Parameter(description = "Product id", example = "1", required = true) Long productId);

}
