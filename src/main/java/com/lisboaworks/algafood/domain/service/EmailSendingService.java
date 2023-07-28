package com.lisboaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

public interface EmailSendingService {

    void send(Message message);

    @Getter
    @Builder
    class Message {

        private Set<String> receivers;
        private String subject;
        private String body;

    }

}
