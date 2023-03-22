package com.lisboaworks.algafood.algafoodapi.config;

import com.lisboaworks.algafood.algafoodapi.notification.Notifier;
import com.lisboaworks.algafood.algafoodapi.service.CustomerActivationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public CustomerActivationService customerActivationService(Notifier notifier) {
        return new CustomerActivationService(notifier);
    }

}
