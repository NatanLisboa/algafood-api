package com.lisboaworks.algafood.core.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;

import java.util.HashMap;

@Configuration
public class SessionStorageConfig {

    @Bean
    public SessionRepository<?> sessionRepository() {
        return new MapSessionRepository(new HashMap<>());
    }

}