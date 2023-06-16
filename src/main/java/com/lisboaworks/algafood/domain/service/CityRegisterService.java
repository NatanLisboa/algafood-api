package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.CityNotFoundException;
import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CityRegisterService {
    
	public static final String CITY_ALREADY_IN_USE_MESSAGE = "City with id %d cannot be deleted because it is already being used by other entities in database";
	private final CityRepository cityRepository;
    private final StateRegisterService stateRegisterService;

    @Transactional
    public City save(City city) {
        Long stateId = city.getState().getId();
        State state = stateRegisterService.findOrThrowException(stateId);
        city.setState(state);
        return cityRepository.save(city);
    }

    @Transactional
    public void delete(Long cityId) {

        try {
            cityRepository.deleteById(cityId);
            cityRepository.flush(); // execute the transactions made so far to the database
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
