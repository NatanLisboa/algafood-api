package com.lisboaworks.algafood.infrastructure.repository;

import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.StateRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class StateRepositoryImpl implements StateRepository {
    
    @PersistenceContext
    private EntityManager manager;
    
    @Override
    public List<State> findAll() {
        TypedQuery<State> query = manager.createQuery("from State", State.class);
        return query.getResultList();
    }

    @Override
    public State findById(Long id) {
        return manager.find(State.class, id);
    }

    @Override
    @Transactional
    public State save(State state) {
        return manager.merge(state);
    }

    @Override
    @Transactional
    public void delete(State state) {
        state = findById(state.getId());
        manager.remove(state);
    }
}
