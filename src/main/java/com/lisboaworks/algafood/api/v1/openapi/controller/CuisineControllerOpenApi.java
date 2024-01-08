package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.CuisineModel;
import com.lisboaworks.algafood.api.v1.model.input.CuisineInput;
import com.lisboaworks.algafood.core.springdoc.PageableParameter;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@SecurityRequirement(name = "security_auth")
public interface CuisineControllerOpenApi {

    @PageableParameter
    PagedModel<CuisineModel> findAll(@Parameter(hidden = true) Pageable pageable);

    CuisineModel findById(Long cuisineId);

    CuisineModel add(CuisineInput cuisineInput);

    CuisineModel update(Long cuisineId, CuisineInput newCuisineInput);

    void delete(Long cuisineId);
}
