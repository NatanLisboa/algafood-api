package com.lisboaworks.algafood.api.controller;


import com.lisboaworks.algafood.api.assembler.CityDTOAssembler;
import com.lisboaworks.algafood.api.assembler.CityInputDisassembler;
import com.lisboaworks.algafood.api.dto.CityDTO;
import com.lisboaworks.algafood.api.dto.input.CityInput;
import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.StateNotFoundException;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.repository.CityRepository;
import com.lisboaworks.algafood.domain.service.CityRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityRegisterService cityRegisterService;

    @Autowired
    private CityDTOAssembler cityDTOAssembler;
    
    @Autowired
    private CityInputDisassembler cityInputDisassembler;
    
    @GetMapping
    public List<CityDTO> findAll() {
        return cityDTOAssembler.toDTOList(cityRepository.findAll());
    }

    @GetMapping("/{id}")
    public CityDTO findById(@PathVariable Long id) {
        City city = cityRegisterService.findOrThrowException(id);
        return cityDTOAssembler.toDTO(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityDTO add(@RequestBody @Valid CityInput cityInput) {
        try {
        	City city = cityInputDisassembler.toDomainObject(cityInput);
            return cityDTOAssembler.toDTO(cityRegisterService.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }
    }


    @PutMapping("/{id}")
    public CityDTO update(@PathVariable Long id,
                       @RequestBody @Valid CityInput newCityInput) {
        try {
            City city = cityRegisterService.findOrThrowException(id);
            cityInputDisassembler.copyToDomainObject(newCityInput, city);
            return cityDTOAssembler.toDTO(cityRegisterService.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cityRegisterService.delete(id);
    }

}
