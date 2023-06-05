package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.CuisineNotFoundException;
import com.lisboaworks.algafood.api.dto.RestaurantDTO;
import com.lisboaworks.algafood.api.dto.CuisineDTO;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantRegisterService restaurantRegisterService;

    @GetMapping
    public List<RestaurantDTO> findAll() {
        return toDTOList(restaurantRepository.findAll());
    }

    @GetMapping("/{restaurantId}")
    public RestaurantDTO findById(@PathVariable Long restaurantId) {
    	Restaurant restaurant = restaurantRegisterService.findOrThrowException(restaurantId);
    	return toDTO(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantDTO add(@RequestBody @Valid Restaurant restaurant) {
        try {
            return toDTO(restaurantRegisterService.save(restaurant));
        } catch (CuisineNotFoundException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestaurantDTO update(@PathVariable Long id,
                                    @RequestBody @Valid Restaurant newRestaurant) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(id);
        BeanUtils.copyProperties(newRestaurant, restaurant, "id", "paymentMethods", "address", "registerDatetime");
        try {
            return toDTO(restaurantRegisterService.save(restaurant));
        } catch (CuisineNotFoundException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }
    
	private RestaurantDTO toDTO(Restaurant restaurant) {
		CuisineDTO cuisine = new CuisineDTO();
    	cuisine.setId(restaurant.getCuisine().getId());
    	cuisine.setName(restaurant.getCuisine().getName());
    	
    	RestaurantDTO restaurantDTO = new RestaurantDTO();
    	restaurantDTO.setId(restaurant.getId());
    	restaurantDTO.setName(restaurant.getName());
    	restaurantDTO.setShippingFee(restaurant.getShippingFee());
    	restaurantDTO.setCuisine(cuisine);
		return restaurantDTO;
	}
	
	private List<RestaurantDTO> toDTOList(List<Restaurant> restaurants) {
		return restaurants.stream()
				.map(restaurant -> toDTO(restaurant))
				.toList();		
	}


}
