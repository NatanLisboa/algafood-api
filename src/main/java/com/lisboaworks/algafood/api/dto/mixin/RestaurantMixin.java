package com.lisboaworks.algafood.api.dto.mixin;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lisboaworks.algafood.domain.model.Address;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.model.PaymentMethod;
import com.lisboaworks.algafood.domain.model.Product;

public abstract class RestaurantMixin {

	@JsonIgnoreProperties(value = "name", allowGetters = true)
    private Cuisine cuisine;

    @JsonIgnore
    private Address address;

    //@JsonIgnore
    private OffsetDateTime registerDatetime;

    //@JsonIgnore
    private OffsetDateTime lastUpdateDatetime;

    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    @JsonIgnore
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

}
