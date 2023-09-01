package com.lisboaworks.algafood.domain.listener;

import com.lisboaworks.algafood.domain.event.ConfirmedOrderEvent;
import com.lisboaworks.algafood.domain.model.Order;
import com.lisboaworks.algafood.domain.service.EmailSendingService;
import com.lisboaworks.algafood.domain.service.EmailSendingService.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
public class ConfirmedOrderCustomerNotificationListener {

    private final EmailSendingService emailSendingService;

    @TransactionalEventListener
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
