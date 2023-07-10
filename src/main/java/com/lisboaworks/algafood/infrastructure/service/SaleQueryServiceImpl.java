package com.lisboaworks.algafood.infrastructure.service;

import com.lisboaworks.algafood.domain.filter.DailySaleFilter;
import com.lisboaworks.algafood.domain.model.Order;
import com.lisboaworks.algafood.domain.model.statistics.DailySale;
import com.lisboaworks.algafood.domain.service.SaleQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

@Repository
public class SaleQueryServiceImpl implements SaleQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<DailySale> getDailySales(DailySaleFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<DailySale> query = builder.createQuery(DailySale.class);
        Root<Order> root = query.from(Order.class);

        Expression<Date> creationDate = builder.function("date", Date.class, root.get("creationDatetime"));

        CompoundSelection<DailySale> selection = builder.construct(
                DailySale.class,
                creationDate,
                builder.count(root.get("id")),
                builder.sum(root.get("totalValue"))
        );

        query.select(selection);
        query.groupBy(creationDate);

        return manager.createQuery(query).getResultList();
    }

}
