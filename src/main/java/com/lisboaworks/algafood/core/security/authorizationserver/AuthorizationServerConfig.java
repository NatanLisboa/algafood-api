package com.lisboaworks.algafood.core.security.authorizationserver;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtKeyStoreProperties jwtKeyStoreProperties;
    private final DataSource dataSource;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//      security.checkTokenAccess("isAuthenticated()");
        security.checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(
                Arrays.asList(new JwtCustomClaimsTokenEnhancer(), this.jwtAccessTokenConverter())
        );

        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .reuseRefreshTokens(false)
                .accessTokenConverter(this.jwtAccessTokenConverter())
                .tokenEnhancer(enhancerChain)
                .approvalStore(this.approvalStore(endpoints.getTokenStore()))
                .tokenGranter(tokenGranter(endpoints));
    }

    @Bean
    public JWKSet jwkSet() {
        RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) this.keyPair().getPublic())
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .keyID("algafood-key-id");

        return new JWKSet(builder.build());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(this.keyPair());

        return jwtAccessTokenConverter;
    }

    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        var pkceAuthorizationCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(),
                endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory());

        var granters = Arrays.asList(
                pkceAuthorizationCodeTokenGranter, endpoints.getTokenGranter());

        return new CompositeTokenGranter(granters);
    }

    private ApprovalStore approvalStore(TokenStore tokenStore) {
        TokenApprovalStore approvalStore = new TokenApprovalStore();
        approvalStore.setTokenStore(tokenStore);

        return approvalStore;
    }

    private KeyPair keyPair() {
        String keyStorePass = jwtKeyStoreProperties.getPassword();
        String keyPairAlias = jwtKeyStoreProperties.getKeypairAlias();

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(jwtKeyStoreProperties.getJksLocation(), keyStorePass.toCharArray());
        return keyStoreKeyFactory.getKeyPair(keyPairAlias);
    }
}
