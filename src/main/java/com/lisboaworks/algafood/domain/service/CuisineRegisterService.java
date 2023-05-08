package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CuisineRegisterService {

    public static final String CUISINE_NOT_FOUND_MESSAGE = "Cuisine entity with id %d could not be found in database";
    public static final String CUISINE_ALREADY_IN_USE_MESSAGE = "Cuisine with id %d cannot be deleted because it is already in use in another table";
    @Autowired
    private CuisineRepository cuisineRepository;

    public Cuisine save(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }

    public void delete(Long id) {
        try {
            cuisineRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format(CUISINE_NOT_FOUND_MESSAGE, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyInUseException(String.format(CUISINE_ALREADY_IN_USE_MESSAGE, id));
        }
    }

    public Cuisine findOrThrowException(Long cuisineId) {
        return cuisineRepository.findById(cuisineId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(CUISINE_NOT_FOUND_MESSAGE, cuisineId)));
    }

}
