package com.lisboaworks.algafood.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Component
@Validated
@ConfigurationProperties("algafood.email")
@Getter
@Setter
public class EmailProperties {

    @NotNull
    private String sender;

    @NotNull
    private EmailSendingServiceImpl impl = EmailSendingServiceImpl.MOCK;

    private Sandbox sandbox = new Sandbox();

    @Getter
    @Setter
    public class Sandbox {

        private String receiver;

    }

    public enum EmailSendingServiceImpl {
        MOCK, SANDBOX, SMTP
    }

}
