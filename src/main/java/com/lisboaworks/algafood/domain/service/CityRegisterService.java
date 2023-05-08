package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.CityRepository;
import com.lisboaworks.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityRegisterService {

    public static final String CITY_NOT_FOUND_MESSAGE = "There is no city with id %d in database";
    public static final String CITY_ALREADY_IN_USE_MESSAGE = "City with id %d cannot be deleted because it is already being used by other entities in database";
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public City save(City city) {
        Long stateId = city.getState().getId();
        Optional<State> optionalState = stateRepository.findById(stateId);

        if (optionalState.isEmpty()) {
            throw new EntityNotFoundException(String.format(CITY_NOT_FOUND_MESSAGE, stateId));
        }

        city.setState(optionalState.get());

        return cityRepository.save(city);
    }

    public void delete(Long id) {

        try {
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format(CITY_NOT_FOUND_MESSAGE, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyInUseException(String.format(CITY_ALREADY_IN_USE_MESSAGE, id));
        }

    }

    public City findOrThrowException(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(CITY_NOT_FOUND_MESSAGE, cityId)));
    }
}
