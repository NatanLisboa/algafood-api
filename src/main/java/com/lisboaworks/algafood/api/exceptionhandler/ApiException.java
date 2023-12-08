package com.lisboaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiException {


    private Integer status;


    private String type;


    private String title;


    private String detail;


    private String userMessage;


    private List<ApiException.Object> objects;


    private OffsetDateTime timestamp;

    @ApiModel("Errors")
    @Getter
    @Builder
    public static class Object {


        private String name;


        private String userMessage;

    }

}
