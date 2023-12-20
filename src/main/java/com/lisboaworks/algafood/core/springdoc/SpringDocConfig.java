package com.lisboaworks.algafood.core.springdoc;

import com.lisboaworks.algafood.api.exceptionhandler.ApiException;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                )).components(new Components().schemas(
                        this.generateSchemas()
                ));
    }

    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return openApi -> {
            openApi.getPaths().values()
                    .forEach(pathItem -> pathItem.readOperationsMap()
                            .forEach((httpMethod, operation) -> {
                                ApiResponses responses = operation.getResponses();
                                switch (httpMethod) {
                                    case GET:
                                        responses.addApiResponse("404", new ApiResponse().description("Resource " +
                                                "not found"));
                                        responses.addApiResponse("406", new ApiResponse().description("Resource " +
                                                "does not have representation that could be accepted by consumer"));
                                        responses.addApiResponse("500", new ApiResponse().description("Internal " +
                                                "server error"));
                                        break;
                                    case POST:
                                        responses.addApiResponse("400", new ApiResponse().description("Bad request"));
                                        responses.addApiResponse("500", new ApiResponse().description("Internal " +
                                                "server error"));
                                        break;
                                    case PUT:
                                        responses.addApiResponse("400", new ApiResponse().description("Bad request"));
                                        responses.addApiResponse("404", new ApiResponse().description("Resource " +
                                                "not found"));
                                        responses.addApiResponse("500", new ApiResponse().description("Internal " +
                                                "server error"));
                                        break;
                                    case DELETE:
                                        responses.addApiResponse("404", new ApiResponse().description("Resource " +
                                                "not found"));
                                        responses.addApiResponse("500", new ApiResponse().description("Internal " +
                                                "server error"));
                                        break;
                                    default:
                                        responses.addApiResponse("500", new ApiResponse().description("Internal " +
                                                "server error"));
                                }
                            }));
        };
    }

    private Map<String, Schema> generateSchemas() {
        final Map<String, Schema> schemaMap = new HashMap<>();

        Map<String, Schema> apiExceptionSchema = ModelConverters.getInstance().read(ApiException.class);
        Map<String, Schema> apiExceptionObjectSchema = ModelConverters.getInstance().read(ApiException.Object.class);

        schemaMap.putAll(apiExceptionSchema);
        schemaMap.putAll(apiExceptionObjectSchema);

        return schemaMap;
    }

}
