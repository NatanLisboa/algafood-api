package com.lisboaworks.algafood.infrastructure.repository;

import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

@Repository
public class CuisineRepositoryImpl implements CuisineRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cuisine> findAll() {
        TypedQuery<Cuisine> query = manager.createQuery("from Cuisine", Cuisine.class);
        return query.getResultList();
    }

    @Override
    public Cuisine findById(Long id) {
        return manager.find(Cuisine.class, id);
    }

    @Override
    public List<Cuisine> findByName(String name) {
        return manager.createQuery("from Cuisine where name like :name", Cuisine.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    @Override
    @Transactional
    public Cuisine save(Cuisine cuisine) {
        return manager.merge(cuisine);
    }

    @Transactional
    public void delete(Long id) {
        Cuisine cuisine = findById(id);

        if (Objects.isNull(cuisine)) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(cuisine);
    }

}
