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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cities")
@Api(tags = "Cities")
@AllArgsConstructor
public class CityController {

    private final CityRepository cityRepository;
    private final CityRegisterService cityRegisterService;
    private final CityDTOAssembler cityDTOAssembler;
    private final CityInputDisassembler cityInputDisassembler;
    
    @GetMapping
    @ApiOperation("Get all registered cities")
    public List<CityDTO> findAll() {
        return cityDTOAssembler.toDTOList(cityRepository.findAll());
    }

    @GetMapping("/{cityId}")
    @ApiOperation("Get a city by its id")
    public CityDTO findById(@PathVariable Long cityId) {
        City city = cityRegisterService.findOrThrowException(cityId);
        return cityDTOAssembler.toDTO(city);
    }

    @PostMapping
    @ApiOperation("Register a new city")
    @ResponseStatus(HttpStatus.CREATED)
    public CityDTO add(@RequestBody @Valid CityInput cityInput) {
        try {
        	City city = cityInputDisassembler.toDomainObject(cityInput);
            return cityDTOAssembler.toDTO(cityRegisterService.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityId}")
    @ApiOperation("Update an existing city")
    public CityDTO update(@PathVariable Long cityId,
                       @RequestBody @Valid CityInput newCityInput) {
        try {
            City city = cityRegisterService.findOrThrowException(cityId);
            cityInputDisassembler.copyToDomainObject(newCityInput, city);
            return cityDTOAssembler.toDTO(cityRegisterService.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{cityId}")
    @ApiOperation("Delete a city by its id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cityId) {
        cityRegisterService.delete(cityId);
    }


}
