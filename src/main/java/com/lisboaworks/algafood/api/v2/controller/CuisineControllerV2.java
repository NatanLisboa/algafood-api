package com.lisboaworks.algafood.api.v2.controller;

import com.lisboaworks.algafood.api.v2.assembler.CuisineInputDisassemblerV2;
import com.lisboaworks.algafood.api.v2.assembler.CuisineModelAssemblerV2;
import com.lisboaworks.algafood.api.v2.model.CuisineModelV2;
import com.lisboaworks.algafood.api.v2.model.input.CuisineInputV2;
import com.lisboaworks.algafood.api.v2.openapi.CuisineControllerOpenApiV2;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import com.lisboaworks.algafood.domain.service.CuisineRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v2/cuisines", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CuisineControllerV2 implements CuisineControllerOpenApiV2 {

    private final CuisineRepository cuisineRepository;
    private final CuisineRegisterService cuisineRegisterService;
    private final CuisineModelAssemblerV2 cuisineModelAssembler;
    private final CuisineInputDisassemblerV2 cuisineInputDisassembler;
    private final PagedResourcesAssembler<Cuisine> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<CuisineModelV2> findAll(@PageableDefault(size = 5) Pageable pageable) {
        Page<Cuisine> cuisinesPage = cuisineRepository.findAll(pageable);
        PagedModel<CuisineModelV2> cuisinesPagedModel = pagedResourcesAssembler
                .toModel(cuisinesPage, cuisineModelAssembler);
        return cuisinesPagedModel;
    }

    @GetMapping("/{cuisineId}")
    public CuisineModelV2 findById(@PathVariable Long cuisineId) {
    	Cuisine cuisine = cuisineRegisterService.findOrThrowException(cuisineId);
        return cuisineModelAssembler.toModel(cuisine);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuisineModelV2 add(@Valid @RequestBody CuisineInputV2 cuisineInput) {
    	Cuisine cuisine = cuisineInputDisassembler.toDomainObject(cuisineInput);
        return cuisineModelAssembler.toModel(cuisineRegisterService.save(cuisine));
    }

    @PutMapping("/{cuisineId}")
    public CuisineModelV2 update(@PathVariable Long cuisineId,
                               @RequestBody @Valid CuisineInputV2 newCuisineInput) {
        Cuisine cuisine = cuisineRegisterService.findOrThrowException(cuisineId);
        cuisineInputDisassembler.copyToDomainObject(newCuisineInput, cuisine);
        return cuisineModelAssembler.toModel(cuisineRegisterService.save(cuisine));
    }

    @DeleteMapping("/{cuisineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cuisineId) {
        cuisineRegisterService.delete(cuisineId);
    }

}
