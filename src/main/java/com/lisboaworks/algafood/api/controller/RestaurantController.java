package com.lisboaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lisboaworks.algafood.api.assembler.RestaurantDTOAssembler;
import com.lisboaworks.algafood.api.dto.RestaurantDTO;
import com.lisboaworks.algafood.api.dto.input.RestaurantInput;
import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.CuisineNotFoundException;
import com.lisboaworks.algafood.domain.model.Cuisine;
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
        	Restaurant restaurant = toDomainObject(restaurantInput);
            return restaurantDTOAssembler.toDTO(restaurantRegisterService.save(restaurant));
        } catch (CuisineNotFoundException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestaurantDTO update(@PathVariable Long id,
                                    @RequestBody @Valid RestaurantInput newRestaurantInput) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(id);
        Restaurant newRestaurant = toDomainObject(newRestaurantInput);
        BeanUtils.copyProperties(newRestaurant, restaurant, "id", "paymentMethods", "address", "registerDatetime");
        try {
            return restaurantDTOAssembler.toDTO(restaurantRegisterService.save(restaurant));
        } catch (CuisineNotFoundException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }
	
	private Restaurant toDomainObject(RestaurantInput restaurantInput) {
		Restaurant restaurant = new Restaurant();
		restaurant.setName(restaurantInput.getName());
		restaurant.setShippingFee(restaurantInput.getShippingFee());
		
		Cuisine cuisine = new Cuisine();
		cuisine.setId(restaurantInput.getCuisine().getId());
		
		restaurant.setCuisine(cuisine);
		
		return restaurant;
	}


}
