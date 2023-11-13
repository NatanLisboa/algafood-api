package com.lisboaworks.algafood.infrastructure.service.email;

import com.lisboaworks.algafood.domain.service.EmailSendingService;
import com.lisboaworks.algafood.infrastructure.service.email.exception.EmailException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Component
@AllArgsConstructor
public class EmailTemplateProcessor {

    private final Configuration freeMarkerConfig;

    public String processTemplate(EmailSendingService.Message message) {
        try {
            Template template = freeMarkerConfig.getTemplate(message.getBody());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariables());
        } catch (Exception e) {
            throw new EmailException("Unable to build e-mail template", e);
        }
    }

}
