package com.lisboaworks.algafood.infrastructure.repository.spec;

import com.lisboaworks.algafood.domain.model.Order;
import com.lisboaworks.algafood.domain.filter.OrderFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderSpecs {

    public static Specification<Order> usingFilter(OrderFilter filter) {
        return ((root, query, builder) -> {
            if (Order.class.equals(query.getResultType())) {
                root.fetch("restaurant").fetch("cuisine");
                root.fetch("customer");
            }

            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(filter.getCustomerId())) {
                predicates.add(builder.equal(root.get("customer"), filter.getCustomerId()));
            }

            if (Objects.nonNull(filter.getRestaurantId())) {
                predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
            }

            if (Objects.nonNull(filter.getStartCreationDatetime())) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("creationDatetime"), filter.getStartCreationDatetime()));
            }

            if (Objects.nonNull(filter.getEndCreationDatetime())) {
                predicates.add(builder.lessThanOrEqualTo(root.get("creationDatetime"), filter.getEndCreationDatetime()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }

}
