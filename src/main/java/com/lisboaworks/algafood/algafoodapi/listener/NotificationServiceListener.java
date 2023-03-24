package com.lisboaworks.algafood.algafoodapi.listener;

import com.lisboaworks.algafood.algafoodapi.event.ActivatedCustomerEvent;
import com.lisboaworks.algafood.algafoodapi.notification.Notifier;
import com.lisboaworks.algafood.algafoodapi.notification.NotifierType;
import com.lisboaworks.algafood.algafoodapi.notification.UrgencyLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceListener {

    @NotifierType(UrgencyLevel.NO_URGENT)
    @Autowired
    private Notifier notifier;

    @EventListener
    public void activatedCustomerListener(ActivatedCustomerEvent event) {
        notifier.notify(event.getCustomer(), "Your system register is active!");
    }


}
