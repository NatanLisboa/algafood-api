package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantRegisterService restaurantRegisterService;

    @Autowired
    private CuisineRepository cuisineRepository;


    @GetMapping
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id);

        if (Objects.isNull(restaurant)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(restaurant);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Restaurant restaurant) {
        try {
            restaurant = restaurantRegisterService.save(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Restaurant newRestaurant) {
        try {
            Restaurant restaurant = restaurantRepository.findById(id);

            if (Objects.isNull(restaurant)) {
                return ResponseEntity.notFound().build();
            }

            BeanUtils.copyProperties(newRestaurant, restaurant, "id");

            restaurant = restaurantRegisterService.save(restaurant);

            return ResponseEntity.ok(restaurant);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
