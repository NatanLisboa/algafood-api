package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

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

}
