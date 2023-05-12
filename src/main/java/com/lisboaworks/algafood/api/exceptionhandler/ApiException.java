package com.lisboaworks.algafood.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiException {

    private LocalDateTime datetime;
    private String message;


}
