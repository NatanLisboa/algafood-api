package com.lisboaworks.algafood.infrastructure.repository.spec;

import com.lisboaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {

    public static Specification<Restaurant> withoutShippingFee() {
        return ((root, query, builder) -> builder.equal(root.get("shippingFee"), BigDecimal.ZERO));
    }

    public static Specification<Restaurant> withSimilarName(String restaurantName) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + restaurantName + "%");
    }

//    public static Specification<Restaurant> genericRestaurantEqualSpec(String fieldName, Object value) {
//        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName), value));
//    }

}
