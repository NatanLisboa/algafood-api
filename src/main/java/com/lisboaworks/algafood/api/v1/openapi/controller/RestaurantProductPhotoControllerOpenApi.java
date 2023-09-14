package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import com.lisboaworks.algafood.api.v1.model.ProductPhotoModel;
import com.lisboaworks.algafood.api.v1.model.input.ProductPhotoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "Products")
public interface RestaurantProductPhotoControllerOpenApi {

    @ApiOperation(value = "Get product photo", produces = "application/json, image/jpeg, image/png")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid restaurant or product id", content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant or product not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    ProductPhotoModel getPhoto(@ApiParam(value = "Id from a restaurant", example = "1", required = true) Long restaurantId,
                               @ApiParam(value = "Id from a product", example = "1", required = true) Long productId);

    @ApiOperation(value = "Update product photo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product photo updated successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant or product not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    ProductPhotoModel updatePhoto(@ApiParam(value = "Id from a restaurant", example = "1", required = true) Long restaurantId,
                                  @ApiParam(value = "Id from a product", example = "1", required = true) Long productId,
                                  @ApiParam(name = "body", value = "Product photo new data representation", required = true) ProductPhotoInput productPhotoInput,
                                  @ApiParam(value = "Product photo (must be JPEG or PNG with max size of 500KB)") MultipartFile file) throws IOException;

    @ApiOperation(value = "Serve restaurant product photo image", hidden = true)
    ResponseEntity<InputStreamResource> servePhoto(Long restaurantId, Long productId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;

    @ApiOperation(value = "Delete product photo")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Product photo deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid restaurant or product id", content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant or product not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    void deletePhoto(@ApiParam(value = "Id from a restaurant", example = "1", required = true) Long restaurantId,
                     @ApiParam(value = "Id from a product", example = "1", required = true) Long productId);

}
