package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.Cuisine;

import java.util.List;

public interface CuisineRepository {

    List<Cuisine> findAll();

    Cuisine findById(Long id);

    List<Cuisine> findByName(String name);

    Cuisine save(Cuisine cuisine);

    void delete(Long id);


}
