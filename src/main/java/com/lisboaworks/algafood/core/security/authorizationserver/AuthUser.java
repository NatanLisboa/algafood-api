package com.lisboaworks.algafood.core.security.authorizationserver;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthUser extends User {

    private final Long userId;
    private final String fullName;

    public AuthUser(com.lisboaworks.algafood.domain.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);

        this.userId = user.getId();
        this.fullName = user.getName();
    }

}
