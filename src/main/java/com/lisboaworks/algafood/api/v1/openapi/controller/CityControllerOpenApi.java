package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import com.lisboaworks.algafood.api.v1.model.CityModel;
import com.lisboaworks.algafood.api.v1.model.input.CityInput;
import org.springframework.hateoas.CollectionModel;

public interface CityControllerOpenApi {

    CollectionModel<CityModel> findAll();

    CityModel findById(Long cityId);


    CityModel add(CityInput cityInput);

    CityModel update(@ApiParam(value = "Id from a city", example = "1", required = true)
                          Long cityId,

                     @ApiParam(name = "body", value = "City representation with new data")
                          CityInput newCityInput);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "City deleted successfully"),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Delete a city by its id")
    void delete(@ApiParam(value = "Id from a city", example = "1", required = true)
                       Long cityId);
}
