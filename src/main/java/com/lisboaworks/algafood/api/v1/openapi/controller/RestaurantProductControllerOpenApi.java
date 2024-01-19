package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.ProductModel;
import com.lisboaworks.algafood.api.v1.model.input.ProductInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Products")
public interface RestaurantProductControllerOpenApi {

    @Operation(summary = "Get all the restaurant products")
    CollectionModel<ProductModel> findAll(@Parameter(description = "Restaurant id", example = "1", required = true)
                                          Long restaurantId,
                                          @Parameter(description = "Flag to include inactive products", example = "false")
                                          Boolean includeInactiveProducts);

    @Operation(summary = "Get a restaurant product by its id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Restaurant or product not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ProductModel findById(@Parameter(description = "Restaurant id", example = "1", required = true) Long restaurantId,
                          @Parameter(description = "Product id", example = "1", required = true) Long productId);

    @Operation(summary = "Register a restaurant product", responses = {
            @ApiResponse(responseCode = "201", description = "Product registered successfully")
    })
    ProductModel add(@Parameter(description = "Restaurant id", example = "1", required = true) Long restaurantId,
                     @RequestBody(description = "Representation of a new product", required = true)
                     ProductInput productInput);

    @Operation(summary = "Update a restaurant product by its id", responses = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant or product not found",
                    content = @Content(schema = @Schema(ref = "ApiException"))
            )
    })
    ProductModel update(@Parameter(description = "Restaurant id", example = "1", required = true) Long restaurantId,
                        @Parameter(description = "Product id", example = "1", required = true) Long productId,
                        @RequestBody(description = "Representation of a product with updated data", required = true)
                        ProductInput updateProductInput);

}
