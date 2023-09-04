package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.RestaurantInputDisassembler;
import com.lisboaworks.algafood.api.assembler.RestaurantModelAssembler;
import com.lisboaworks.algafood.api.assembler.RestaurantOnlyNameModelAssembler;
import com.lisboaworks.algafood.api.assembler.RestaurantSummaryModelAssembler;
import com.lisboaworks.algafood.api.model.RestaurantModel;
import com.lisboaworks.algafood.api.model.RestaurantOnlyNameModel;
import com.lisboaworks.algafood.api.model.RestaurantSummaryModel;
import com.lisboaworks.algafood.api.model.input.RestaurantInput;
import com.lisboaworks.algafood.api.openapi.controller.RestaurantControllerOpenApi;
import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.CityNotFoundException;
import com.lisboaworks.algafood.domain.exception.CuisineNotFoundException;
import com.lisboaworks.algafood.domain.exception.RestaurantNotFoundException;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RestaurantController implements RestaurantControllerOpenApi {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantRegisterService restaurantRegisterService;
    private final RestaurantModelAssembler restaurantModelAssembler;
    private final RestaurantInputDisassembler restaurantInputDisassembler;
    private final RestaurantSummaryModelAssembler restaurantSummaryModelAssembler;
    private final RestaurantOnlyNameModelAssembler restaurantOnlyNameModelAssembler;

    @GetMapping
    public CollectionModel<RestaurantSummaryModel> findAll() {
        return restaurantSummaryModelAssembler.toCollectionModel(restaurantRepository.findAll());
    }

    @GetMapping(params = "projection=only-name")
    public CollectionModel<RestaurantOnlyNameModel> findAllOnlyWithName() {
        return restaurantOnlyNameModelAssembler.toCollectionModel(restaurantRepository.findAll());
    }

    @GetMapping("/{restaurantId}")
    public RestaurantModel findById(@PathVariable Long restaurantId) {
    	Restaurant restaurant = restaurantRegisterService.findOrThrowException(restaurantId);
    	return restaurantModelAssembler.toModel(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantModel add(@RequestBody @Valid RestaurantInput restaurantInput) {
        try {
        	Restaurant restaurant = restaurantInputDisassembler.toDomainObject(restaurantInput);
            return restaurantModelAssembler.toModel(restaurantRegisterService.save(restaurant));
        } catch (CuisineNotFoundException | CityNotFoundException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}")
    public RestaurantModel update(@PathVariable Long restaurantId,
                                  @RequestBody @Valid RestaurantInput newRestaurantInput) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(restaurantId);
        restaurantInputDisassembler.copyToDomainObject(newRestaurantInput, restaurant);
        try {
            return restaurantModelAssembler.toModel(restaurantRegisterService.save(restaurant));
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

    @PutMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateMultiples(@RequestBody List<Long> restaurantIds) {
        try {
            restaurantRegisterService.activate(restaurantIds);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivateMultiples(@RequestBody List<Long> restaurantIds) {
        try {
            restaurantRegisterService.inactivate(restaurantIds);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }
    }

}
