package com.lisboaworks.algafood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.lisboaworks.algafood.api"))
                    .paths(PathSelectors.any())
//                  .paths(PathSelectors.ant("/restaurants/*"))
                .build()
                .apiInfo(this.apiInfo())
                .tags(new Tag("Cities", "Manage the cities"));
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Algafood API")
                .description("Open API to customers and restaurants")
                .version("1")
                .contact(new Contact("Natan da Fonseca Lisboa", "https://github.com/NatanLisboa", "natanflisboa1@gmail.com"))
                .build();
    }

}
