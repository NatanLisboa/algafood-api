package com.lisboaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.Map;
import java.util.Set;

public interface EmailSendingService {

    void send(Message message);

    @Getter
    @Builder
    class Message {

        @Singular
        private Set<String> receivers;

        @NonNull
        private String subject;

        @NonNull
        private String body;

        @Singular
        private Map<String, Object> variables;

    }

}
