package com.lisboaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiException {

    @Schema(example = "400")
    private Integer status;

    @Schema(example = "https://algafood.com.br/invalid-data")
    private String type;

    @Schema(example = "Invalid data")
    private String title;

    @Schema(example = "One or more fields are invalid. Fill in correctly and try again.")
    private String detail;

    @Schema(example = "One or more fields are invalid. Fill in correctly and try again.")
    private String userMessage;

    @Schema(description = "List of objects or fields that generated the errors")
    private List<ApiException.Object> objects;

    @Schema(example = "2023-12-20T11:21:50.982245498Z")
    private OffsetDateTime timestamp;

    @Getter
    @Builder
    @Schema(name = "ApiException.Object")
    public static class Object {

        @Schema(example = "price")
        private String name;

        @Schema(example = "The price is invalid")
        private String userMessage;

    }

}
