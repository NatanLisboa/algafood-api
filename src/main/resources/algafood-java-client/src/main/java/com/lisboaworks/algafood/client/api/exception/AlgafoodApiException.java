package com.lisboaworks.algafood.client.api.exception;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class AlgafoodApiException {

    private Integer status;
    private OffsetDateTime timestamp;
    private String userMessage;
    private List<Object> objects = new ArrayList<>();

    @Data
    public static class Object {

        private String name;
        private String userMessage;

    }

}
