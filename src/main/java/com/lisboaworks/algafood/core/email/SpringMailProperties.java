package com.lisboaworks.algafood.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.mail")
@Getter
@Setter
public class SpringMailProperties {

    private String host;
    private int port;
    private String username;
    private String password;

}
