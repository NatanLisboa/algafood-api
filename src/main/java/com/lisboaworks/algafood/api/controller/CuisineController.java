package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.CuisineDTOAssembler;
import com.lisboaworks.algafood.api.assembler.CuisineInputDisassembler;
import com.lisboaworks.algafood.api.dto.CuisineDTO;
import com.lisboaworks.algafood.api.dto.input.CuisineInput;
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
    private final CuisineDTOAssembler cuisineDTOAssembler;
    private final CuisineInputDisassembler cuisineInputDisassembler;

    @GetMapping
    public Page<CuisineDTO> findAll(@PageableDefault(size = 5) Pageable pageable) {
        Page<Cuisine> cuisinesPage = cuisineRepository.findAll(pageable);
        List<CuisineDTO> cuisinesDTO = cuisineDTOAssembler.toDTOList(cuisinesPage.getContent());
        Page<CuisineDTO> cuisinesDTOPage = new PageImpl<>(cuisinesDTO, pageable, cuisinesPage.getTotalElements());
        return cuisinesDTOPage;
    }

    @GetMapping("/{cuisineId}")
    public CuisineDTO findById(@PathVariable Long cuisineId) {
    	Cuisine cuisine = cuisineRegisterService.findOrThrowException(cuisineId);
        return cuisineDTOAssembler.toDTO(cuisine);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuisineDTO add(@Valid @RequestBody CuisineInput cuisineInput) {
    	Cuisine cuisine = cuisineInputDisassembler.toDomainObject(cuisineInput);
        return cuisineDTOAssembler.toDTO(cuisineRegisterService.save(cuisine));
    }

    @PutMapping("/{cuisineId}")
    public CuisineDTO update(@PathVariable Long cuisineId,
                                          @RequestBody @Valid CuisineInput newCuisineInput) {
        Cuisine cuisine = cuisineRegisterService.findOrThrowException(cuisineId);
        cuisineInputDisassembler.copyToDomainObject(newCuisineInput, cuisine);
        return cuisineDTOAssembler.toDTO(cuisineRegisterService.save(cuisine));
    }

    @DeleteMapping("/{cuisineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cuisineId) {
        cuisineRegisterService.delete(cuisineId);
    }

}
