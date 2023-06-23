package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.RestaurantDTOAssembler;
import com.lisboaworks.algafood.api.assembler.RestaurantInputDisassembler;
import com.lisboaworks.algafood.api.dto.RestaurantDTO;
import com.lisboaworks.algafood.api.dto.input.RestaurantInput;
import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.CityNotFoundException;
import com.lisboaworks.algafood.domain.exception.CuisineNotFoundException;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantRegisterService restaurantRegisterService;
    private final RestaurantDTOAssembler restaurantDTOAssembler;
    private final RestaurantInputDisassembler restaurantInputDisassembler;

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
        } catch (CuisineNotFoundException | CityNotFoundException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}")
    public RestaurantDTO update(@PathVariable Long restaurantId,
                                    @RequestBody @Valid RestaurantInput newRestaurantInput) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(restaurantId);
        restaurantInputDisassembler.copyToDomainObject(newRestaurantInput, restaurant);
        try {
            return restaurantDTOAssembler.toDTO(restaurantRegisterService.save(restaurant));
        } catch (CuisineNotFoundException | CityNotFoundException e) {
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

    @PutMapping("/{restaurantId}/opening")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void open(@PathVariable Long restaurantId) {
        restaurantRegisterService.open(restaurantId);
    }

    @PutMapping("/{restaurantId}/closure")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Long restaurantId) {
        restaurantRegisterService.close(restaurantId);
    }

}
