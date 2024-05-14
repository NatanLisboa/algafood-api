package com.lisboaworks.algafood.core.email;

import com.lisboaworks.algafood.domain.service.EmailSendingService;
import com.lisboaworks.algafood.infrastructure.service.email.MockEmailSendingService;
import com.lisboaworks.algafood.infrastructure.service.email.SandboxEmailSendingService;
import com.lisboaworks.algafood.infrastructure.service.email.SmtpEmailSendingService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@AllArgsConstructor
public class EmailConfig {

    private final EmailProperties emailProperties;
    private final SpringMailProperties springMailProperties;

    @Bean
    public EmailSendingService emailSendingService() {

        switch (emailProperties.getImpl()) {
            case MOCK -> { return new MockEmailSendingService(); }
            case SANDBOX -> { return new SandboxEmailSendingService(); }
            case SMTP -> { return new SmtpEmailSendingService(); }
            default -> { return null; }
        }

    }

    @Bean
    @ConditionalOnProperty(name = "algafood.email.impl", havingValue = "smtp")
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(springMailProperties.getHost());
        javaMailSender.setPort(springMailProperties.getPort());
        javaMailSender.setUsername(springMailProperties.getUsername());
        javaMailSender.setPassword(springMailProperties.getPassword());
        return javaMailSender;
    }

}
