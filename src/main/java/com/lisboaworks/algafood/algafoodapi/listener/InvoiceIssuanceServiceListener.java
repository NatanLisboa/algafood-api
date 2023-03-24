package com.lisboaworks.algafood.algafoodapi.listener;

import com.lisboaworks.algafood.algafoodapi.event.ActivatedCustomerEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InvoiceIssuanceServiceListener {


    @EventListener
    public void activatedCustomerListener(ActivatedCustomerEvent event) {
        System.out.println("Doing issuance of invoice to " + event.getCustomer().getName());
    }


}
