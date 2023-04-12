package com.lisboaworks.algafood.infrastructure.repository;

import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.repository.CityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

@Repository
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> findAll() {
        TypedQuery<City> query = manager.createQuery("from City", City.class);
        return query.getResultList();
    }

    @Override
    public City findById(Long id) {
        return manager.find(City.class, id);
    }

    @Override
    @Transactional
    public City save(City city) {
        return manager.merge(city);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        City city = findById(id);

        if (Objects.isNull(city)) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(city);
    }
}
