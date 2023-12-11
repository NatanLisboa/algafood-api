package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.CityModel;
import com.lisboaworks.algafood.api.v1.model.input.CityInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
public interface CityControllerOpenApi {

    CollectionModel<CityModel> findAll();

    CityModel findById(Long cityId);


    CityModel add(CityInput cityInput);

    CityModel update(Long cityId, CityInput newCityInput);

    void delete(Long cityId);

}
