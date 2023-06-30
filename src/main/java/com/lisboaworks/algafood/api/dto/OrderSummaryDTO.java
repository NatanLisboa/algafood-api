package com.lisboaworks.algafood.api.dto;

import com.lisboaworks.algafood.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OrderSummaryDTO {

    private Long id;
    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal totalValue;
    private OffsetDateTime creationDatetime;
    private RestaurantSummaryDTO restaurant;
    private UserDTO customer;
    private OrderStatus status;

}
