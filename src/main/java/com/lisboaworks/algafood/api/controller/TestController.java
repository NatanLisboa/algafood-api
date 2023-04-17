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

import static com.lisboaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.withoutShippingFee;
import static com.lisboaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.withSimilarName;


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

    @GetMapping("/cuisine/exists-by-name")
    public boolean cuisineExistsByName(@RequestParam String name) {
        return cuisineRepository.existsByName(name);
    }


    @GetMapping("/restaurants/count-by-cuisine-id")
    public int countRestaurantsByCuisine(@RequestParam Long cuisineId) {
        return restaurantRepository.countByCuisineId(cuisineId);
    }

    @GetMapping("/cuisine")
    public Optional<Cuisine> getCuisineByName(@RequestParam String name) {
        return cuisineRepository.findByName(name);
    }

//    @GetMapping("/restaurants-by-shipping-fee")
//    public List<Restaurant> getRestaurantsByShippingFee(BigDecimal startFee, BigDecimal endFee) {
//        return restaurantRepository.queryByShippingFeeBetween(startFee, endFee);
//    }

    @GetMapping("/restaurants/by-name-and-cuisine-id")
    public List<Restaurant> getRestaurantsByNameAndCuisineId(String restaurantName, Long cuisineId) {
        return restaurantRepository.retrieveByNameAndCuisineId(restaurantName, cuisineId);
    }

    @GetMapping("/restaurants/by-name-and-shipping-fee")
    public List<Restaurant> getRestaurantsByNameAndShippingFee(String restaurantName, BigDecimal startFee, BigDecimal endFee) {
        return restaurantRepository.findAllMatching(restaurantName, startFee, endFee);
    }

    @GetMapping("/restaurants/first-by-name")
    public Optional<Restaurant> getFirstRestaurantByName(String restaurantName) {
        return restaurantRepository.findFirstRestaurantByNameContaining(restaurantName);
    }

    @GetMapping("/restaurants/top2-by-name")
    public List<Restaurant> getTop2RestaurantsByName(String restaurantName) {
        return restaurantRepository.findTop2ByNameContaining(restaurantName);
    }

    @GetMapping("/restaurants/without-shipping-fee")
    public List<Restaurant> getRestaurantsWithoutShippingFee(String restaurantName) {
        return restaurantRepository.findWithoutShippingFee(restaurantName);
    }
}
