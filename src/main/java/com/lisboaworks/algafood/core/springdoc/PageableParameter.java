package com.lisboaworks.algafood.core.springdoc;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(
        in = ParameterIn.QUERY,
        name = "page",
        description = "Page number",
        schema = @Schema(type = "integer", defaultValue = "0")
)
@Parameter(
        in = ParameterIn.QUERY,
        name = "size",
        description = "Page size",
        schema = @Schema(type = "integer", defaultValue = "5")
)
@Parameter(
        in = ParameterIn.QUERY,
        name = "sort",
        description = "Sorting properties (e.g. name,asc)",
        examples = {
                @ExampleObject("name"),
                @ExampleObject("name,asc"),
                @ExampleObject("name,desc")
        }
)
public @interface PageableParameter { }
