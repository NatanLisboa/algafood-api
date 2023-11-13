package com.lisboaworks.algafood.infrastructure.service.email;

import com.lisboaworks.algafood.domain.service.EmailSendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MockEmailSendingService implements EmailSendingService {

    @Autowired
    private EmailTemplateProcessor emailTemplateProcessor;

    @Override
    public void send(Message message) {
        log.info(emailTemplateProcessor.processTemplate(message));
    }

}
