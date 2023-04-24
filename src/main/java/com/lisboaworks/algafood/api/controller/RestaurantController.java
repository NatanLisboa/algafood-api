package com.lisboaworks.algafood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);

        return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
            Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

            if (optionalRestaurant.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Restaurant restaurant = optionalRestaurant.get();

            BeanUtils.copyProperties(newRestaurant, restaurant, "id", "paymentMethods", "address", "registerDatetime");

            restaurant = restaurantRegisterService.save(restaurant);

            return ResponseEntity.ok(restaurant);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partiallyUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fieldsToUpdate) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

        if (optionalRestaurant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Restaurant restaurant = optionalRestaurant.get();

        merge(fieldsToUpdate, restaurant);

        return update(restaurant.getId(), restaurant);
    }

    private void merge(Map<String, Object> sourceFields, Restaurant targetObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant restaurant = objectMapper.convertValue(sourceFields, Restaurant.class);

        sourceFields.forEach((propertyField, propertyValue) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, propertyField);
            assert field != null;
            field.setAccessible(true);
            Object newPropertyValue = ReflectionUtils.getField(field, restaurant);
            ReflectionUtils.setField(field, targetObject, newPropertyValue);
        });
    }

}
