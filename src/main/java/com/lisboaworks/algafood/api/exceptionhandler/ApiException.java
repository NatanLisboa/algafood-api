package com.lisboaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiException {

    @ApiModelProperty(example = "400", position = 1)
    private Integer status;

    @ApiModelProperty(example = "https://algafood.com.br/invalid-data", position = 5)
    private String type;

    @ApiModelProperty(example = "Invalid data", position = 10)
    private String title;

    @ApiModelProperty(example = "One or more fields are invalid. Fill in correctly and try again.", position = 15)
    private String detail;

    @ApiModelProperty(example = "One or more fields are invalid. Fill in correctly and try again.", position = 20)
    private String userMessage;

    @ApiModelProperty(value = "Objects or fields list that generated errors (optional)", position = 25)
    private List<ApiException.Object> objects;

    @ApiModelProperty(example = "2023-08-14T17:11:42.6760735Z", position = 30)
    private OffsetDateTime timestamp;

    @ApiModel("Errors")
    @Getter
    @Builder
    public static class Object {

        @ApiModelProperty("price")
        private String name;

        @ApiModelProperty("The price is mandatory")
        private String userMessage;

    }

}
