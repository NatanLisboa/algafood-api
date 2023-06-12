package com.lisboaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.lisboaworks.algafood.api.assembler.RestaurantDTOAssembler;
import com.lisboaworks.algafood.api.assembler.RestaurantInputDisassembler;
import com.lisboaworks.algafood.api.dto.RestaurantDTO;
import com.lisboaworks.algafood.api.dto.input.RestaurantInput;
import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.CuisineNotFoundException;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantRegisterService restaurantRegisterService;
    
    @Autowired
    private RestaurantDTOAssembler restaurantDTOAssembler;
    
    @Autowired
    private RestaurantInputDisassembler restaurantInputDisassembler;

    @GetMapping
    public List<RestaurantDTO> findAll() {
        return restaurantDTOAssembler.toDTOList(restaurantRepository.findAll());
    }

    @GetMapping("/{restaurantId}")
    public RestaurantDTO findById(@PathVariable Long restaurantId) {
    	Restaurant restaurant = restaurantRegisterService.findOrThrowException(restaurantId);
    	return restaurantDTOAssembler.toDTO(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantDTO add(@RequestBody @Valid RestaurantInput restaurantInput) {
        try {
        	Restaurant restaurant = restaurantInputDisassembler.toDomainObject(restaurantInput);
            return restaurantDTOAssembler.toDTO(restaurantRegisterService.save(restaurant));
        } catch (CuisineNotFoundException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestaurantDTO update(@PathVariable Long id,
                                    @RequestBody @Valid RestaurantInput newRestaurantInput) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(id);
        restaurantInputDisassembler.copyToDomainObject(newRestaurantInput, restaurant);
        try {
            return restaurantDTOAssembler.toDTO(restaurantRegisterService.save(restaurant));
        } catch (CuisineNotFoundException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long restaurantId) {
        restaurantRegisterService.activate(restaurantId);
    }

    @DeleteMapping("/{restaurantId}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivate(@PathVariable Long restaurantId) {
        restaurantRegisterService.inactivate(restaurantId);
    }

}
