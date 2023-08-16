package com.lisboaworks.algafood.core.openapi;

import com.fasterxml.classmate.TypeResolver;
import com.lisboaworks.algafood.api.dto.CuisineDTO;
import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import com.lisboaworks.algafood.api.openapi.dto.CuisinesDTOOpenApi;
import com.lisboaworks.algafood.api.openapi.dto.PageableDTOOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        TypeResolver typeResolver = new TypeResolver();


        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.lisboaworks.algafood.api"))
                    .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, this.globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, this.globalPostPutResponseMessages())
                .globalResponses(HttpMethod.PUT, this.globalPostPutResponseMessages())
                .globalResponses(HttpMethod.DELETE, this.globalDeleteResponseMessages())
                .additionalModels(typeResolver.resolve(ApiException.class))
                .directModelSubstitute(Pageable.class, PageableDTOOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(Page.class, CuisineDTO.class),
                        CuisinesDTOOpenApi.class)
                )
                .apiInfo(this.apiInfo())
                .tags(new Tag("Cities", "Manage the cities"))
                .tags(new Tag("User groups", "Manage the user groups"))
                .tags(new Tag("Cuisines", "Manage the cuisines"));
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Algafood API")
                .description("Open API to customers and restaurants")
                .version("1")
                .contact(new Contact("Natan da Fonseca Lisboa", "https://github.com/NatanLisboa", "natanflisboa1@gmail.com"))
                .build();
    }

    private List<Response> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                        .description("Internal server error")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(this.apiExceptionBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE))
                        .description("Resource has no representation that can be accepted by the consumer")
                        .build()
        );
    }

    private List<Response> globalPostPutResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST))
                        .description("Bad request (client error)")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(this.apiExceptionBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                        .description("Internal server error")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(this.apiExceptionBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE))
                        .description("Resource has no representation that can be accepted by the consumer")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE))
                        .description("Request denied because the body is in an unsupported format")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(this.apiExceptionBuilder())
                        .build()
        );
    }

    private List<Response> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST))
                        .description("Bad request (client error)")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(this.apiExceptionBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                        .description("Internal server error")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(this.apiExceptionBuilder())
                        .build()
        );
    }

    private Consumer<RepresentationBuilder> apiExceptionBuilder() {
        return r -> r.model(m -> m.name("ApiException")
                .referenceModel(
                        ref -> ref.key(
                                k -> k.qualifiedModelName(
                                        q -> q.name("ApiException")
                                                .namespace("com.lisboaworks.algafood.api.exceptionhandler")
                                ))));
    }

}
