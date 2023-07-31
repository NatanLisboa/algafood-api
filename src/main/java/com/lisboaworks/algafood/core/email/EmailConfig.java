package com.lisboaworks.algafood.core.email;

import com.lisboaworks.algafood.core.email.EmailProperties.EmailSendingServiceImpl;
import com.lisboaworks.algafood.domain.service.EmailSendingService;
import com.lisboaworks.algafood.infrastructure.service.email.MockEmailSendingService;
import com.lisboaworks.algafood.infrastructure.service.email.SmtpEmailSendingService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class EmailConfig {

    private final EmailProperties emailProperties;

    @Bean
    public EmailSendingService emailSendingService() {
        if (emailProperties.getImpl().equals(EmailSendingServiceImpl.MOCK)) {
            return new MockEmailSendingService();
        } else {
            return new SmtpEmailSendingService();
        }
    }

}
