package com.lisboaworks.algafood.api.v1.controller;


import com.lisboaworks.algafood.api.ResourceUriHelper;
import com.lisboaworks.algafood.api.v1.assembler.CityInputDisassembler;
import com.lisboaworks.algafood.api.v1.assembler.CityModelAssembler;
import com.lisboaworks.algafood.api.v1.model.CityModel;
import com.lisboaworks.algafood.api.v1.model.input.CityInput;
import com.lisboaworks.algafood.api.v1.openapi.controller.CityControllerOpenApi;
import com.lisboaworks.algafood.core.web.AlgaMediaTypes;
import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.StateNotFoundException;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.repository.CityRepository;
import com.lisboaworks.algafood.domain.service.CityRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cities", produces = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CityController implements CityControllerOpenApi {

    private final CityRepository cityRepository;
    private final CityRegisterService cityRegisterService;
    private final CityModelAssembler cityModelAssembler;
    private final CityInputDisassembler cityInputDisassembler;
    
    @GetMapping
    public CollectionModel<CityModel> findAll() {
        List<City> cities = cityRepository.findAll();
        return cityModelAssembler.toCollectionModel(cities);
    }

    @GetMapping("/{cityId}")
    public CityModel findById(@PathVariable Long cityId) {
        City city = cityRegisterService.findOrThrowException(cityId);
        return cityModelAssembler.toModel(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityModel add(@RequestBody @Valid CityInput cityInput) {
        try {
        	City city = cityInputDisassembler.toDomainObject(cityInput);
            CityModel cityModel = cityModelAssembler.toModel(cityRegisterService.save(city));
            ResourceUriHelper.addUriInResponseHeader(cityModel.getId());
            return cityModel;
        } catch (StateNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityId}")
    public CityModel update(@PathVariable Long cityId, @RequestBody @Valid CityInput newCityInput) {
        try {
            City city = cityRegisterService.findOrThrowException(cityId);
            cityInputDisassembler.copyToDomainObject(newCityInput, city);
            return cityModelAssembler.toModel(cityRegisterService.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cityId) {
        cityRegisterService.delete(cityId);
    }

}
