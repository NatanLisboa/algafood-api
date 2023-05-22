package com.lisboaworks.algafood.api.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.CuisineNotFoundException;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/{restaurantId}")
    public Restaurant findById(@PathVariable Long restaurantId) {
        return restaurantRegisterService.findOrThrowException(restaurantId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant add(@Valid @RequestBody Restaurant restaurant) {
        try {
            return restaurantRegisterService.save(restaurant);
        } catch (CuisineNotFoundException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Restaurant update(@PathVariable Long id,
                                    @RequestBody Restaurant newRestaurant) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(id);
        BeanUtils.copyProperties(newRestaurant, restaurant, "id", "paymentMethods", "address", "registerDatetime");
        try {
            return restaurantRegisterService.save(restaurant);
        } catch (CuisineNotFoundException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public Restaurant partiallyUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fieldsToUpdate,
                                      HttpServletRequest request) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(id);
        merge(fieldsToUpdate, restaurant, request);
        return update(restaurant.getId(), restaurant);
    }

    private void merge(Map<String, Object> sourceFields, Restaurant targetObject, HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurant restaurant = objectMapper.convertValue(sourceFields, Restaurant.class);

            sourceFields.forEach((propertyField, propertyValue) -> {
                Field field = ReflectionUtils.findField(Restaurant.class, propertyField);
                assert field != null;
                field.setAccessible(true);
                Object newPropertyValue = ReflectionUtils.getField(field, restaurant);
                ReflectionUtils.setField(field, targetObject, newPropertyValue);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }

    }

}
