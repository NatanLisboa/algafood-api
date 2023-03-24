package com.lisboaworks.algafood.jpa;

import com.lisboaworks.algafood.domain.model.Cuisine;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CuisineRegister {

    @PersistenceContext
    private EntityManager manager;

    public List<Cuisine> getAll() {
        TypedQuery<Cuisine> query = manager.createQuery("from Cuisine", Cuisine.class);
        return query.getResultList();
    }

    @Transactional
    public Cuisine register(Cuisine cuisine) {
        return manager.merge(cuisine);
    }

}
