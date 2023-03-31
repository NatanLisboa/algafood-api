package com.lisboaworks.algafood.infrastructure.repository;

import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.repository.CityRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> findAll() {
        TypedQuery<City> query = manager.createQuery("from City", City.class);
        return query.getResultList();
    }

    @Override
    public City find(Long id) {
        return manager.find(City.class, id);
    }

    @Override
    @Transactional
    public City save(City city) {
        return manager.merge(city);
    }

    @Override
    @Transactional
    public void delete(City city) {
        city = find(city.getId());
        manager.remove(city);
    }
}
