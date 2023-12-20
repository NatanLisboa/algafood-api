package com.lisboaworks.algafood.core.springdoc;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.servers.ServerVariables;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@SecurityScheme(name = "security_auth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(authorizationCode = @OAuthFlow(
            authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
            tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
            scopes = {
                    @OAuthScope(name = "READ", description = "read scope"),
                    @OAuthScope(name = "WRITE", description = "write scope")
            }
        )))
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Algafood API")
                        .version("v1")
                        .description("Algafood RESTful API documentation")
                        .license(new License()
                                .name("MIT")
                                .url("http://opensource.org/licenses/MIT"))
                ).externalDocs(new ExternalDocumentation()
                        .description("SpringDoc")
                        .url("https://springdoc.org")
                ).servers(List.of(
                        new Server().description("Local").url("http://localhost:8080"),
                        new Server().description("Local (Docker)").url("http://localhost"),
                        new Server().description("Production").url("https://www.algafoodapi.com.br")
                )).tags(Arrays.asList(
                        new Tag().name("Cities").description("Manage the cities")
                ));
    }

    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return openApi -> {
            openApi.getPaths().values()
                    .stream()
                    .flatMap(pathItem -> pathItem.readOperations().stream())
                    .forEach(operation -> {
                        ApiResponses responses = operation.getResponses();

                        ApiResponse resourceNotFound = new ApiResponse().description("Resource not found");
                        ApiResponse withoutRepresentation = new ApiResponse().description("Resource does not have a representation " +
                                "that could be accepted by consumer");
                        ApiResponse internalServerError = new ApiResponse().description("Internal server error");

                        responses.addApiResponse("404", resourceNotFound);
                        responses.addApiResponse("406", withoutRepresentation);
                        responses.addApiResponse("500", internalServerError);

                    });
        };
    }

}
