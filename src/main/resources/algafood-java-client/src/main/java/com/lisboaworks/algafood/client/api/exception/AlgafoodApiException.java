package com.lisboaworks.algafood.client.api.exception;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AlgafoodApiException {

    private Integer status;
    private OffsetDateTime timestamp;
    private String userMessage;

}
