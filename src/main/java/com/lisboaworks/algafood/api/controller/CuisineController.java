package com.lisboaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lisboaworks.algafood.api.assembler.CuisineDTOAssembler;
import com.lisboaworks.algafood.api.assembler.CuisineInputDisassembler;
import com.lisboaworks.algafood.api.dto.CuisineDTO;
import com.lisboaworks.algafood.api.dto.input.CuisineInput;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import com.lisboaworks.algafood.domain.service.CuisineRegisterService;

@RestController
@RequestMapping("/cuisines")
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CuisineRegisterService cuisineRegisterService;
    
    @Autowired
    public CuisineDTOAssembler cuisineDTOAssembler;
    
    @Autowired
    public CuisineInputDisassembler cuisineInputDisassembler;
    

    @GetMapping
    public List<CuisineDTO> findAll() {
        return cuisineDTOAssembler.toDTOList(cuisineRepository.findAll());
    }

    @GetMapping("/{id}")
    public CuisineDTO findById(@PathVariable Long id) {
    	Cuisine cuisine = cuisineRegisterService.findOrThrowException(id);
        return cuisineDTOAssembler.toDTO(cuisine);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuisineDTO add(@Valid @RequestBody CuisineInput cuisineInput) {
    	Cuisine cuisine = cuisineInputDisassembler.toDomainObject(cuisineInput);
        return cuisineDTOAssembler.toDTO(cuisineRegisterService.save(cuisine));
    }

    @PutMapping("/{id}")
    public CuisineDTO update(@PathVariable Long id,
                                          @RequestBody @Valid CuisineInput newCuisineInput) {
        Cuisine cuisine = cuisineRegisterService.findOrThrowException(id);
        cuisineInputDisassembler.copyToDomainObject(newCuisineInput, cuisine);
        return cuisineDTOAssembler.toDTO(cuisineRegisterService.save(cuisine));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cuisineRegisterService.delete(id);
    }

}
