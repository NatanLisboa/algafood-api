package com.lisboaworks.algafood.api.dto;

import com.lisboaworks.algafood.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private String code;
    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal totalValue;
    private OffsetDateTime creationDatetime;
    private OffsetDateTime confirmationDatetime;
    private OffsetDateTime cancellationDatetime;
    private OffsetDateTime deliveryDatetime;
    private PaymentMethodDTO paymentMethod;
    private RestaurantSummaryDTO restaurant;
    private UserDTO customer;
    private AddressDTO deliveryAddress;
    private OrderStatus status;
    private List<OrderItemDTO> items;

}
