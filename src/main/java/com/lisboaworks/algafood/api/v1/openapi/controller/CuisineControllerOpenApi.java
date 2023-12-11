package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.CuisineModel;
import com.lisboaworks.algafood.api.v1.model.input.CuisineInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@SecurityRequirement(name = "security_auth")
public interface CuisineControllerOpenApi {

    PagedModel<CuisineModel> findAll(Pageable pageable);

    CuisineModel findById(Long cuisineId);

    CuisineModel add(CuisineInput cuisineInput);

    CuisineModel update(Long cuisineId, CuisineInput newCuisineInput);

    void delete(Long cuisineId);
}
