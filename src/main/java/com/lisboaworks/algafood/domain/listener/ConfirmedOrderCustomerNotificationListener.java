package com.lisboaworks.algafood.domain.listener;

import com.lisboaworks.algafood.domain.event.ConfirmedOrderEvent;
import com.lisboaworks.algafood.domain.model.Order;
import com.lisboaworks.algafood.domain.service.EmailSendingService;
import com.lisboaworks.algafood.domain.service.EmailSendingService.Message;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConfirmedOrderCustomerNotificationListener {

    private final EmailSendingService emailSendingService;

    @EventListener
    public void whenConfirmingOrder(ConfirmedOrderEvent event) {
        Order order = event.getOrder();

        Message message = Message.builder()
                .subject(order.getRestaurant().getName() + " - Confirmed order")
                .body("confirmed-order.html")
                .variable("order", order)
                .receiver(order.getCustomer().getEmail())
                .build();

        emailSendingService.send(message);
    }

}
