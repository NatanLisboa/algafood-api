package com.lisboaworks.algafood.api.openapi.controller;

import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import com.lisboaworks.algafood.api.model.ProductModel;
import com.lisboaworks.algafood.api.model.input.ProductInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Products")
public interface RestaurantProductControllerOpenApi {

    @ApiOperation("Get all products from restaurant")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid restaurant id", content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    CollectionModel<ProductModel> findAll(@ApiParam(value = "Id from a restaurant", example = "1", required = true) Long restaurantId,
                               @ApiParam(value = "Flag to include inactive products (default: false)", example = "false") Boolean includeInactiveProducts);

    @ApiOperation("Get restaurant product by its id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid restaurant or product id", content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant or product not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    ProductModel findById(@ApiParam(value = "Id from a restaurant", example = "1", required = true) Long restaurantId,
                          @ApiParam(value = "Id from a product", example = "1", required = true) Long productId);

    @ApiOperation("Register a new product on restaurant")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Restaurant product registered successfully"),
    })
    ProductModel add(@ApiParam(value = "Id from a restaurant", example = "1", required = true) Long restaurantId,
                     @ApiParam(name = "body", value = "New restaurant product representation", required = true) ProductInput productInput);

    @ApiOperation("Update an existing restaurant product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurant product updated successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant or product not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    ProductModel update(@ApiParam(value = "Id from a restaurant", example = "1", required = true) Long restaurantId,
                        @ApiParam(value = "Id from a product", example = "1", required = true) Long productId,
                        @ApiParam(name = "body", value = "Restaurant product representation with new data", required = true) ProductInput updateProductInput);

}
