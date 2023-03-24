package com.lisboaworks.algafood.algafoodapi.notification;

import com.lisboaworks.algafood.algafoodapi.model.Customer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@NotifierType(UrgencyLevel.NO_URGENT)
@Component
public class EmailNotifierMock implements Notifier {

    public EmailNotifierMock() {
        System.out.println("MOCK EmailNotifier");
    }

    @Override
    public void notify(Customer customer, String message) {
        System.out.printf("MOCK: Notifying would be sent to %s by email %s: %s\n",
                customer.getName(), customer.getEmail(), message);
    }
}
