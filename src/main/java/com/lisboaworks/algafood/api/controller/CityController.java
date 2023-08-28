package com.lisboaworks.algafood.api.controller;


import com.lisboaworks.algafood.api.ResourceUriHelper;
import com.lisboaworks.algafood.api.assembler.CityDTOAssembler;
import com.lisboaworks.algafood.api.assembler.CityInputDisassembler;
import com.lisboaworks.algafood.api.dto.CityDTO;
import com.lisboaworks.algafood.api.dto.input.CityInput;
import com.lisboaworks.algafood.api.openapi.controller.CityControllerOpenApi;
import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.StateNotFoundException;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.repository.CityRepository;
import com.lisboaworks.algafood.domain.service.CityRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CityController implements CityControllerOpenApi {

    private final CityRepository cityRepository;
    private final CityRegisterService cityRegisterService;
    private final CityDTOAssembler cityDTOAssembler;
    private final CityInputDisassembler cityInputDisassembler;
    
    @GetMapping
    public List<CityDTO> findAll() {
        return cityDTOAssembler.toDTOList(cityRepository.findAll());
    }

    @GetMapping("/{cityId}")
    public CityDTO findById(@PathVariable Long cityId) {
        City city = cityRegisterService.findOrThrowException(cityId);
        CityDTO cityDTO = cityDTOAssembler.toDTO(city);

//      cityDTO.add(Link.of("http://api.algafood.local:8080/cities/1", IanaLinkRelations.SELF));
        cityDTO.add(Link.of("http://api.algafood.local:8080/cities/1"));

//      cityDTO.add(Link.of("http://api.algafood.local:8080/cities", IanaLinkRelations.COLLECTION));
        cityDTO.add(Link.of("http://api.algafood.local:8080/cities", "cities"));

        cityDTO.getState().add(Link.of("http://api.algafood.local:8080/states/1"));

        return cityDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityDTO add(@RequestBody @Valid CityInput cityInput) {
        try {
        	City city = cityInputDisassembler.toDomainObject(cityInput);
            CityDTO cityDTO = cityDTOAssembler.toDTO(cityRegisterService.save(city));
            ResourceUriHelper.addUriInResponseHeader(cityDTO.getId());
            return cityDTO;
        } catch (StateNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityId}")
    public CityDTO update(@PathVariable Long cityId, @RequestBody @Valid CityInput newCityInput) {
        try {
            City city = cityRegisterService.findOrThrowException(cityId);
            cityInputDisassembler.copyToDomainObject(newCityInput, city);
            return cityDTOAssembler.toDTO(cityRegisterService.save(city));
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
