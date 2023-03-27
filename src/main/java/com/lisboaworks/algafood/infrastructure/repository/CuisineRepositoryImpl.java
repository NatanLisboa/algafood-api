package com.lisboaworks.algafood.infrastructure.repository;

import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CuisineRepositoryImpl implements CuisineRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cuisine> findAll() {
        TypedQuery<Cuisine> query = manager.createQuery("from Cuisine", Cuisine.class);
        return query.getResultList();
    }

    @Override
    public Cuisine find(Long id) {
        return manager.find(Cuisine.class, id);
    }

    @Override
    @Transactional
    public Cuisine save(Cuisine cuisine) {
        return manager.merge(cuisine);
    }

    @Override
    @Transactional
    public void remove(Cuisine cuisine) {
        cuisine = find(cuisine.getId());
        manager.remove(cuisine);
    }

}
