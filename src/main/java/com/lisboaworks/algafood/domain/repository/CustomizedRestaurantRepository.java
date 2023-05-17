package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface CustomizedRestaurantRepository {

    List<Restaurant> findAllMatching(String name, BigDecimal startShippingFee, BigDecimal endShippingFee);

    List<Restaurant> findWithoutShippingFee(String restaurantName);

}
