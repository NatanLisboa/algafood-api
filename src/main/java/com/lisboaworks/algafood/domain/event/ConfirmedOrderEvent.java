package com.lisboaworks.algafood.domain.event;

import com.lisboaworks.algafood.domain.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConfirmedOrderEvent {

    private Order order;

}
