package com.lisboaworks.algafood.infrastructure.repository.spec;

import com.lisboaworks.algafood.domain.filter.OrderFilter;
import com.lisboaworks.algafood.domain.model.Order;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
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
                predicates.add(builder.equal(root.get("customer").get("id"), filter.getCustomerId()));
            }

            if (Objects.nonNull(filter.getRestaurantId())) {
                predicates.add(builder.equal(root.get("restaurant").get("id"), filter.getRestaurantId()));
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
