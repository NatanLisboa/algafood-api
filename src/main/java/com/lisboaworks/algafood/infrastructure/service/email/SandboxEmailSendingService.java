package com.lisboaworks.algafood.infrastructure.service.email;

import com.lisboaworks.algafood.core.email.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class SandboxEmailSendingService extends SmtpEmailSendingService {

    @Autowired
    private EmailProperties emailProperties;

    @Override
    protected MimeMessage createMimeMessage(Message message) throws MessagingException {
        MimeMessage mimeMessage = super.createMimeMessage(message);

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(emailProperties.getSandbox().getReceiver());

        return mimeMessage;
    }

}
