package com.lisboaworks.algafood.infrastructure.repository;

import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.CustomizedRestaurantRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
         var jpql = new StringBuilder();
         var parameters = new HashMap<String, Object>();
         jpql.append("from Restaurant where 0 = 0 ");

         if (StringUtils.hasLength(name)) {
             jpql.append("and name like :name ");
             parameters.put("name", "%" + name + "%");
         }

         if (Objects.nonNull(startShippingFee)) {
             jpql.append("and shippingFee >= :startFee ");
             parameters.put("startFee", startShippingFee);
         }

         if (Objects.nonNull(endShippingFee)) {
             jpql.append("and shippingFee <= :endFee ");
             parameters.put("endFee", endShippingFee);
         }

         TypedQuery<Restaurant> query = manager.createQuery(jpql.toString(), Restaurant.class);

         parameters.forEach(query::setParameter);

         return query.getResultList();
    }


}
