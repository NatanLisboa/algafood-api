package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.CuisineNotFoundException;
import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CuisineRegisterService {

    public static final String CUISINE_ALREADY_IN_USE_MESSAGE = "Cuisine with id %d cannot be deleted because it is already in use";
    private final CuisineRepository cuisineRepository;

    @Transactional
    public Cuisine save(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }

    @Transactional
    public void delete(Long cuisineId) {
        try {
            cuisineRepository.deleteById(cuisineId);
            cuisineRepository.flush(); // execute the transactions made so far to the database
        } catch (EmptyResultDataAccessException e) {
            throw new CuisineNotFoundException(cuisineId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyInUseException(String.format(CUISINE_ALREADY_IN_USE_MESSAGE, cuisineId));
        }
    }

    public Cuisine findOrThrowException(Long cuisineId) {
        return cuisineRepository.findById(cuisineId)
                .orElseThrow(() -> new CuisineNotFoundException(cuisineId));
    }

}
