package com.lisboaworks.algafood.algafoodapi.notification;

import com.lisboaworks.algafood.algafoodapi.model.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@NotifierType(UrgencyLevel.URGENT)
@Component
public class SMSNotifier implements Notifier {
    @Override
    public void notify(Customer customer, String message) {
        System.out.printf("Notifying %s by phone number %s: %s\n",
                customer.getName(), customer.getPhone(), message);
    }
}
