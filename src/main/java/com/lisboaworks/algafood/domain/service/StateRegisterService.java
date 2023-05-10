package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StateRegisterService {

    public static final String STATE_NOT_FOUND_MESSAGE = "There is no state with id %d in database";
    public static final String STATE_ALREADY_IN_USE_MESSAGE = "State with id %d cannot be deleted because it is already being used by other entities in database";
    @Autowired
    private StateRepository stateRepository;

    public State save(State state) {
        return stateRepository.save(state);
    }

    public void delete(Long id) {

        try {
            stateRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format(STATE_NOT_FOUND_MESSAGE, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyInUseException(String.format(STATE_ALREADY_IN_USE_MESSAGE, id));
        }

    }

    public State findOrThrowException(Long stateId) {
        return stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(STATE_NOT_FOUND_MESSAGE, stateId)));
    }
    
}
