package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long> {

    List<Cuisine> findCuisinesByNameContaining(String name); // CONTAINING: Query com "like"

    Optional<Cuisine> findByName(String name);


}
