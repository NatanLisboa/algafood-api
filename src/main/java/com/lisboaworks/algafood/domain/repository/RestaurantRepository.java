package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
