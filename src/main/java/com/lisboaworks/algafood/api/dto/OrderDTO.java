package com.lisboaworks.algafood.api.dto;

import com.lisboaworks.algafood.domain.model.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    @ApiModelProperty(example = "317a132b-7506-4e5d-aa6f-e7fc6af21248")
    private String code;

    @ApiModelProperty(example = "100.00")
    private BigDecimal subtotal;

    @ApiModelProperty(example = "10.00")
    private BigDecimal shippingFee;

    @ApiModelProperty(example = "110.00")
    private BigDecimal totalValue;

    @ApiModelProperty(example = "2023-07-08T11:00:00Z")
    private OffsetDateTime creationDatetime;

    @ApiModelProperty(example = "2023-07-09T11:00:00Z")
    private OffsetDateTime confirmationDatetime;

    @ApiModelProperty(example = "2023-07-08T16:00:00Z")
    private OffsetDateTime cancellationDatetime;

    @ApiModelProperty(example = "2023-07-11T11:00:00Z")
    private OffsetDateTime deliveryDatetime;

    private PaymentMethodDTO paymentMethod;
    private RestaurantSummaryDTO restaurant;
    private UserDTO customer;
    private AddressDTO deliveryAddress;
    private OrderStatus status;
    private List<OrderItemDTO> items;

}
