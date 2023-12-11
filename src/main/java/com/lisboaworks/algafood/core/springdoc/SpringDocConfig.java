package com.lisboaworks.algafood.core.springdoc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
                );

    }

}
