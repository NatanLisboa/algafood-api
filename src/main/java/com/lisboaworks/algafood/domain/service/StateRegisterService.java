package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.exception.StateNotFoundException;
import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StateRegisterService {
    
	public static final String STATE_ALREADY_IN_USE_MESSAGE = "State with id %d cannot be deleted because it is already being used by other entities in database";
    private final StateRepository stateRepository;

    @Transactional
    public State save(State state) {
        return stateRepository.save(state);
    }

    @Transactional
    public void delete(Long stateId) {

        try {
            stateRepository.deleteById(stateId);
            stateRepository.flush(); // execute the transactions made so far to the database
        } catch (EmptyResultDataAccessException e) {
            throw new StateNotFoundException(stateId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyInUseException(String.format(STATE_ALREADY_IN_USE_MESSAGE, stateId));
        }

    }

    public State findOrThrowException(Long stateId) {
        return stateRepository.findById(stateId)
                .orElseThrow(() -> new StateNotFoundException(stateId));
    }
    
}
