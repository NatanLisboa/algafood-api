package com.lisboaworks.algafood.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.httpBasic()
            .and()
            .authorizeRequests()
                .antMatchers("/v1/cuisines/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .csrf().disable();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(User.builder()
                .username("algafood")
                .password(this.passwordEncoder().encode("123456"))
                .roles("ADMIN")
                .build());
        userDetailsList.add(User.builder()
                .username("algafood-2")
                .password(this.passwordEncoder().encode("123456"))
                .roles("ADMIN")
                .build());
        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
