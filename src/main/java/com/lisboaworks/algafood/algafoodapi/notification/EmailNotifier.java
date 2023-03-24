package com.lisboaworks.algafood.algafoodapi.notification;

import com.lisboaworks.algafood.algafoodapi.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@NotifierType(UrgencyLevel.NO_URGENT)
@Component
public class EmailNotifier implements Notifier {

    @Value("${notifier.email.host-server}")
    private String host;

    @Value("${notifier.email.server-port}")
    private Integer port;

    public EmailNotifier() {
        System.out.println("REAL EmailNotifier");
    }

    public void notify(Customer customer, String message) {
        System.out.println("Host: " + host);
        System.out.println("Port: " + port);


        System.out.printf("Notifying %s by email %s: %s\n",
                customer.getName(), customer.getEmail(), message);
    }

}
