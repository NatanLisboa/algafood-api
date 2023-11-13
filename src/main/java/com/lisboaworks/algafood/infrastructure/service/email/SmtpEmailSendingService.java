package com.lisboaworks.algafood.infrastructure.service.email;

import com.lisboaworks.algafood.core.email.EmailProperties;
import com.lisboaworks.algafood.domain.service.EmailSendingService;
import com.lisboaworks.algafood.infrastructure.service.email.exception.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SmtpEmailSendingService implements EmailSendingService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private EmailTemplateProcessor emailTemplateProcessor;

    @Override
    public void send(Message message) {
        try {
            MimeMessage mimeMessage = this.createMimeMessage(message);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Unable to send e-mail", e);
        }

    }

    protected MimeMessage createMimeMessage(Message message) throws MessagingException {
        String body = emailTemplateProcessor.processTemplate(message);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom(emailProperties.getSender());
        helper.setTo(message.getReceivers().toArray(new String[0]));
        helper.setSubject(message.getSubject());
        helper.setText(body, true);

        return mimeMessage;
    }

}
