package com.lisboaworks.algafood.infrastructure.repository;

import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.CustomizedRestaurantRepository;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.lisboaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.withSimilarName;
import static com.lisboaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.withoutShippingFee;

@Repository
public class RestaurantRepositoryImpl implements CustomizedRestaurantRepository {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    @Lazy
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findAllMatching(String name, BigDecimal startShippingFee, BigDecimal endShippingFee) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class); // from Restaurant

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(name)) {
            Predicate namePredicate = builder.like(root.get("name"), "%" + name + "%");
            predicates.add(namePredicate);
        }

        if (Objects.nonNull(startShippingFee)) {
            Predicate startFeePredicate = builder.greaterThanOrEqualTo(root.get("shippingFee"), startShippingFee);
            predicates.add(startFeePredicate);
        }

        if (Objects.nonNull(endShippingFee)) {
            Predicate endFeePredicate = builder.lessThanOrEqualTo(root.get("shippingFee"), endShippingFee);
            predicates.add(endFeePredicate);
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurant> query = manager.createQuery(criteria);

        return query.getResultList();

    }

    @Override
    public List<Restaurant> findWithoutShippingFee(String restaurantName) {
        return restaurantRepository.findAll(withoutShippingFee().and(withSimilarName(restaurantName)));
    }


}
