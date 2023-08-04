package com.lisboaworks.algafood.core.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*"); //default: Simple request methods (GET, HEAD, POST)
                //.allowedOrigins("*") //default: All origins (*)
                //.maxAge(30);  //Preflight request cache life (default: 1800 seconds)
    }

}
