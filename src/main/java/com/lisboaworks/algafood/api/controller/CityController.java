package com.lisboaworks.algafood.api.controller;


import com.lisboaworks.algafood.api.assembler.CityDTOAssembler;
import com.lisboaworks.algafood.api.assembler.CityInputDisassembler;
import com.lisboaworks.algafood.api.dto.CityDTO;
import com.lisboaworks.algafood.api.dto.input.CityInput;
import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.StateNotFoundException;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.repository.CityRepository;
import com.lisboaworks.algafood.domain.service.CityRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid city id",
                    content = @Content(schema = @Schema(implementation = ApiException.class))),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Get a city by its id")
    public CityDTO findById(@ApiParam(value = "Id from a city", example = "1")
                            @PathVariable Long cityId) {
        City city = cityRegisterService.findOrThrowException(cityId);
        return cityDTOAssembler.toDTO(city);
    }

    @PostMapping
    @ApiOperation("Register a new city")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Registered city")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public CityDTO add(@ApiParam(name = "body", value = "New city representation")
                       @RequestBody @Valid CityInput cityInput) {
        try {
        	City city = cityInputDisassembler.toDomainObject(cityInput);
            return cityDTOAssembler.toDTO(cityRegisterService.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated city"),
            @ApiResponse(responseCode = "404", description = "City not found", content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Update an existing city")
    public CityDTO update(@ApiParam(value = "Id from a city", example = "1")
                          @PathVariable Long cityId,

                          @ApiParam(name = "body", value = "City representation with new data")
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
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "City deleted successfully"),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content(schema = @Schema(implementation = ApiException.class)))
    })
    @ApiOperation("Delete a city by its id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiParam(value = "Id from a city", example = "1")
                       @PathVariable Long cityId) {
        cityRegisterService.delete(cityId);
    }


}
