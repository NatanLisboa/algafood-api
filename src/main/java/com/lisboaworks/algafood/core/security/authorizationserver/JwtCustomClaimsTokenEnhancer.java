package com.lisboaworks.algafood.core.security.authorizationserver;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class JwtCustomClaimsTokenEnhancer implements TokenEnhancer {


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (authentication.getPrincipal() instanceof AuthUser) {
            AuthUser authUser = (AuthUser) authentication.getPrincipal();

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("full_name", authUser.getFullName());
            userInfo.put("user_id", authUser.getUserId());

            DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
            oAuth2AccessToken.setAdditionalInformation(userInfo);
        }

        return accessToken;
    }

}
