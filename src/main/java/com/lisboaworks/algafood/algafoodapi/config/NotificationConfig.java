package com.lisboaworks.algafood.algafoodapi.config;

import com.lisboaworks.algafood.algafoodapi.notification.EmailNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    @Bean
    public EmailNotifier emailNotifier() {
        EmailNotifier notifier = new EmailNotifier("smtp.algamail.com.br");
        notifier.setUpperCase(true);

        return notifier;
    }

}
