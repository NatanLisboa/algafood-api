package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> findAll();
    Restaurant find(Long id);

    Restaurant save(Restaurant restaurant);

    void delete(Restaurant restaurant);

}
