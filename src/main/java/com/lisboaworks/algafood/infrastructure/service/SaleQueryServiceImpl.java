package com.lisboaworks.algafood.infrastructure.service;

import com.lisboaworks.algafood.domain.filter.DailySaleFilter;
import com.lisboaworks.algafood.domain.model.Order;
import com.lisboaworks.algafood.domain.model.OrderStatus;
import com.lisboaworks.algafood.domain.model.statistics.DailySale;
import com.lisboaworks.algafood.domain.service.SaleQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class SaleQueryServiceImpl implements SaleQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<DailySale> getDailySales(DailySaleFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<DailySale> query = builder.createQuery(DailySale.class);
        Root<Order> root = query.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();
        Expression<Date> creationDate = builder.function("date", Date.class, root.get("creationDatetime"));
        CompoundSelection<DailySale> selection = builder.construct(
                DailySale.class,
                creationDate,
                builder.count(root.get("id")),
                builder.sum(root.get("totalValue"))
        );
        List<OrderStatus> ordersStatusesToBeReturned = Arrays.asList(
                OrderStatus.CONFIRMED,
                OrderStatus.DELIVERED
        );

        predicates.add(root.get("status").in(ordersStatusesToBeReturned));
        if (Objects.nonNull(filter.getRestaurantId())) {
            Predicate restaurantIdPredicate = builder.equal(root.get("restaurant"), filter.getRestaurantId());
            predicates.add(restaurantIdPredicate);
        }
        if (Objects.nonNull(filter.getStartCreationDatetime())) {
            Predicate startCreationDatetimePredicate = builder.greaterThanOrEqualTo(root.get("creationDatetime"), filter.getStartCreationDatetime());
            predicates.add(startCreationDatetimePredicate);
        }
        if (Objects.nonNull(filter.getEndCreationDatetime())) {
            Predicate endCreationDatetimePredicate = builder.lessThanOrEqualTo(root.get("creationDatetime"), filter.getEndCreationDatetime());
            predicates.add(endCreationDatetimePredicate);
        }

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(creationDate);

        return manager.createQuery(query).getResultList();
    }

}
