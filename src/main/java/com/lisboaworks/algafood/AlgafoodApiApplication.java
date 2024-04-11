package com.lisboaworks.algafood;

import com.lisboaworks.algafood.core.io.Base64ProtocolResolver;
import com.lisboaworks.algafood.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodApiApplication {

    public static void main(String[] args) {
    	TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        SpringApplication app = new SpringApplication(AlgafoodApiApplication.class);
        app.addListeners(new Base64ProtocolResolver());
        app.run(args);
    }

}
