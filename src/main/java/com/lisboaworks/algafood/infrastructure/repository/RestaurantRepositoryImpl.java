package com.lisboaworks.algafood.infrastructure.repository;

import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.CustomizedRestaurantRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Repository
public class RestaurantRepositoryImpl implements CustomizedRestaurantRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> findAllMatching(String name, BigDecimal startShippingFee, BigDecimal endShippingFee) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class); // from Restaurant
        Predicate namePredicate = builder.like(root.get("name"), "%" + name + "%");
        Predicate startFeePredicate = builder.greaterThanOrEqualTo(root.get("shippingFee"), startShippingFee);
        Predicate endFeePredicate = builder.lessThanOrEqualTo(root.get("shippingFee"), endShippingFee);

        criteria.where(namePredicate, startFeePredicate, endFeePredicate);

        TypedQuery<Restaurant> query = manager.createQuery(criteria);

        return query.getResultList();

    }


}
