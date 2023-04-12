package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/cuisines")
    public List<Cuisine> getCuisinesByName(@RequestParam String name) {
        return cuisineRepository.findCuisinesByNameContaining(name);
    }

    @GetMapping("/cuisine")
    public Optional<Cuisine> getCuisineByName(@RequestParam String name) {
        return cuisineRepository.findByName(name);
    }

    @GetMapping("/restaurants-by-shipping-fee")
    public List<Restaurant> getRestaurantsByShippingFee(BigDecimal startFee, BigDecimal endFee) {
        return restaurantRepository.findByShippingFeeBetween(startFee, endFee);
    }

    @GetMapping("/restaurants-by-name-and-cuisine-id")
    public List<Restaurant> getRestaurantsByNameAndCuisineId(String restaurantName, Long cuisineId) {
        return restaurantRepository.findByNameContainingAndCuisineId(restaurantName, cuisineId);
    }


}
