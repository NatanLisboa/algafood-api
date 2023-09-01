package com.lisboaworks.algafood.core.email;

import com.lisboaworks.algafood.domain.service.EmailSendingService;
import com.lisboaworks.algafood.infrastructure.service.email.MockEmailSendingService;
import com.lisboaworks.algafood.infrastructure.service.email.SandboxEmailSendingService;
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

        switch (emailProperties.getImpl()) {
            case MOCK -> { return new MockEmailSendingService(); }
            case SANDBOX -> { return new SandboxEmailSendingService(); }
            case SMTP -> { return new SmtpEmailSendingService(); }
            default -> { return null; }
        }

    }

}
