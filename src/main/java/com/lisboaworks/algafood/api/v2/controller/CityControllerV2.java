package com.lisboaworks.algafood.api.v2.controller;

import com.lisboaworks.algafood.api.ResourceUriHelper;
import com.lisboaworks.algafood.api.v2.assembler.CityInputDisassemblerV2;
import com.lisboaworks.algafood.api.v2.assembler.CityModelAssemblerV2;
import com.lisboaworks.algafood.api.v2.model.CityModelV2;
import com.lisboaworks.algafood.api.v2.model.input.CityInputV2;
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
@RequestMapping(path = "/cities")
@AllArgsConstructor
public class CityControllerV2 {

    private final CityRepository cityRepository;
    private final CityRegisterService cityRegisterService;
    private final CityModelAssemblerV2 cityModelAssembler;
    private final CityInputDisassemblerV2 cityInputDisassembler;
    
    @GetMapping(produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    public CollectionModel<CityModelV2> findAll() {
        List<City> cities = cityRepository.findAll();
        return cityModelAssembler.toCollectionModel(cities);
    }

    @GetMapping(value = "/{cityId}", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    public CityModelV2 findById(@PathVariable Long cityId) {
        City city = cityRegisterService.findOrThrowException(cityId);
        return cityModelAssembler.toModel(city);
    }

    @PostMapping(produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CityModelV2 add(@RequestBody @Valid CityInputV2 cityInput) {
        try {
        	City city = cityInputDisassembler.toDomainObject(cityInput);
            CityModelV2 cityModel = cityModelAssembler.toModel(cityRegisterService.save(city));
            ResourceUriHelper.addUriInResponseHeader(cityModel.getCityId());
            return cityModel;
        } catch (StateNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }
    }

    @PutMapping(value = "/{cityId}", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    public CityModelV2 update(@PathVariable Long cityId, @RequestBody @Valid CityInputV2 newCityInput) {
        try {
            City city = cityRegisterService.findOrThrowException(cityId);
            cityInputDisassembler.copyToDomainObject(newCityInput, city);
            return cityModelAssembler.toModel(cityRegisterService.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }

    }

//    Cannot be mapped with the same URL to a different MediaType, as it does not accept input and returns void.
//    @DeleteMapping("/{cityId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable Long cityId) {
//        cityRegisterService.delete(cityId);
//    }

}
