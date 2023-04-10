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
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.Objects;

@Service
public class CityRegisterService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public City save(City city) {
        Long stateId = city.getState().getId();
        State state = stateRepository.findById(stateId);

        if (Objects.isNull(state)) {
            throw new EntityNotFoundException(String.format("There is no state with id %d in database", stateId));
        }

        return cityRepository.save(city);
    }

    public void delete(Long id) {

        try {
            cityRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("There is no city with id %d in database", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyInUseException("City with id %d cannot be deleted because it is already being used by other entities in database");
        }

    }

}
