package com.lisboaworks.algafood.client.api.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientResponseException;

@Slf4j
public class ClientApiException extends RuntimeException {

    @Getter
    private AlgafoodApiException algafoodApiException;

    public ClientApiException(String message, RestClientResponseException cause) {
        super(message, cause);

        this.deserializeAlgafoodApiException(cause);
    }

    private void deserializeAlgafoodApiException(RestClientResponseException cause) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.findAndRegisterModules();

        try {
            this.algafoodApiException = mapper.readValue(cause.getResponseBodyAsString(), AlgafoodApiException.class);
        } catch (JsonProcessingException e) {
            log.warn("Unable to deserialize the response in AlgafoodApiException type object", e);
        }
    }
}
