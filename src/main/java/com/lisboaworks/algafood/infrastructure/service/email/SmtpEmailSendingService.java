package com.lisboaworks.algafood.infrastructure.service.email;

import com.lisboaworks.algafood.core.email.EmailProperties;
import com.lisboaworks.algafood.domain.service.EmailSendingService;
import com.lisboaworks.algafood.infrastructure.service.email.exception.EmailException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class SmtpEmailSendingService implements EmailSendingService {

    private final JavaMailSender mailSender;
    private final EmailProperties emailProperties;
    private final Configuration freeMarkerConfig;

    @Override
    public void send(Message message) {
        try {
            String body = this.processTemplate(message);

            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailProperties.getSender());
            helper.setTo(message.getReceivers().toArray(new String[0]));
            helper.setSubject(message.getSubject());
            helper.setText(body, true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Unable to send e-mail", e);
        }

    }

    private String processTemplate(Message message) {
        try {
            Template template = freeMarkerConfig.getTemplate(message.getBody());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariables());
        } catch (Exception e) {
            throw new EmailException("Unable to build e-mail template", e);
        }
    }

}
