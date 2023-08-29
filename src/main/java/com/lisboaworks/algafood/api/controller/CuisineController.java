package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.CuisineModelAssembler;
import com.lisboaworks.algafood.api.assembler.CuisineInputDisassembler;
import com.lisboaworks.algafood.api.model.CuisineModel;
import com.lisboaworks.algafood.api.model.input.CuisineInput;
import com.lisboaworks.algafood.api.openapi.controller.CuisineControllerOpenApi;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import com.lisboaworks.algafood.domain.service.CuisineRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cuisines", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CuisineController implements CuisineControllerOpenApi {

    private final CuisineRepository cuisineRepository;
    private final CuisineRegisterService cuisineRegisterService;
    private final CuisineModelAssembler cuisineModelAssembler;
    private final CuisineInputDisassembler cuisineInputDisassembler;

    @GetMapping
    public Page<CuisineModel> findAll(@PageableDefault(size = 5) Pageable pageable) {
        Page<Cuisine> cuisinesPage = cuisineRepository.findAll(pageable);
        List<CuisineModel> cuisinesModel = cuisineModelAssembler.toCollectionModel(cuisinesPage.getContent());
        Page<CuisineModel> cuisinesModelPage = new PageImpl<>(cuisinesModel, pageable, cuisinesPage.getTotalElements());
        return cuisinesModelPage;
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
