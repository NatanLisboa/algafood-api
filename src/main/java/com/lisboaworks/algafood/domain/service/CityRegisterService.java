package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.CityNotFoundException;
import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.CityRepository;
import com.lisboaworks.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityRegisterService {
    public static final String CITY_ALREADY_IN_USE_MESSAGE = "City with id %d cannot be deleted because it is already being used by other entities in database";
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateRegisterService stateRegisterService;

    public City save(City city) {
        Long stateId = city.getState().getId();
        State state = stateRegisterService.findOrThrowException(stateId);
        city.setState(state);
        return cityRepository.save(city);
    }

    public void delete(Long cityId) {

        try {
            cityRepository.deleteById(cityId);
        } catch (EmptyResultDataAccessException e) {
            throw new CityNotFoundException(cityId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyInUseException(String.format(CITY_ALREADY_IN_USE_MESSAGE, cityId));
        }

    }

    public City findOrThrowException(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(cityId));
    }
}
