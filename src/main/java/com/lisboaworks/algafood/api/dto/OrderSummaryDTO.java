package com.lisboaworks.algafood.api.dto;

import com.lisboaworks.algafood.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderSummaryDTO {

    private String code;
    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal totalValue;
    private OffsetDateTime creationDatetime;
    private RestaurantSummaryDTO restaurant;
    private UserDTO customer;
    private OrderStatus status;

}
