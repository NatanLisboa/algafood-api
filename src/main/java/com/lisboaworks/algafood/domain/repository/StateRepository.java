package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.model.State;

import java.util.List;

public interface StateRepository {

    List<State> findAll();

    State find(Long id);

    State save(State state);

    void delete(State state);

}
