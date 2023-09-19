package com.lisboaworks.algafood.api.v1.controller;

import com.lisboaworks.algafood.api.v1.assembler.CuisineInputDisassembler;
import com.lisboaworks.algafood.api.v1.assembler.CuisineModelAssembler;
import com.lisboaworks.algafood.api.v1.model.CuisineModel;
import com.lisboaworks.algafood.api.v1.model.input.CuisineInput;
import com.lisboaworks.algafood.api.v1.openapi.controller.CuisineControllerOpenApi;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import com.lisboaworks.algafood.domain.service.CuisineRegisterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(path = "/v1/cuisines", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class CuisineController implements CuisineControllerOpenApi {

    private final CuisineRepository cuisineRepository;
    private final CuisineRegisterService cuisineRegisterService;
    private final CuisineModelAssembler cuisineModelAssembler;
    private final CuisineInputDisassembler cuisineInputDisassembler;
    private final PagedResourcesAssembler<Cuisine> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<CuisineModel> findAll(@PageableDefault(size = 5) Pageable pageable) {
        log.info("Searching for cuisines with pages containing {} records...", pageable.getPageSize());

        if (true) {
            throw new RuntimeException("Exception test");
        }

        Page<Cuisine> cuisinesPage = cuisineRepository.findAll(pageable);
        PagedModel<CuisineModel> cuisinesPagedModel = pagedResourcesAssembler
                .toModel(cuisinesPage, cuisineModelAssembler);
        return cuisinesPagedModel;
    }

    @GetMapping("/{cuisineId}")
    public CuisineModel findById(@PathVariable Long cuisineId) {
    	Cuisine cuisine = cuisineRegisterService.findOrThrowException(cuisineId);
        return cuisineModelAssembler.toModel(cuisine);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuisineModel add(@Valid @RequestBody CuisineInput cuisineInput) {
    	Cuisine cuisine = cuisineInputDisassembler.toDomainObject(cuisineInput);
        return cuisineModelAssembler.toModel(cuisineRegisterService.save(cuisine));
    }

    @PutMapping("/{cuisineId}")
    public CuisineModel update(@PathVariable Long cuisineId,
                               @RequestBody @Valid CuisineInput newCuisineInput) {
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
