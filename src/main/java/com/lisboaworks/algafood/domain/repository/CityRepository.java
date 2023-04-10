package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> findAll();

    City findById(Long id);

    City save(City city);

    void delete(Long id);

}
