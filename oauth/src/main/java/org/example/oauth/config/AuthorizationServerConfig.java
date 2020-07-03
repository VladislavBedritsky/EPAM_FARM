package org.example.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * AuthorizationServerConfigurerAdapter child class
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Value("${config.oauth2.privateKey}")
    private String privateKey;
    @Value("${config.oauth2.publicKey}")
    private String publicKey;
    @Value("${config.oauth2.redirectUri1}")
    private String uri1;
    @Value("${config.oauth2.redirectUri2}")
    private String uri2;
    @Value("${config.oauth2.redirectUri3}")
    private String uri3;
    @Value("${config.oauth2.redirectUri4}")
    private String uri4;
    @Value("${config.oauth2.clientId}")
    private String clientId;
    @Value("${config.oauth2.secret}")
    private String secret;
    @Value("${config.oauth2.grantTypes}")
    private String grantTypes;
    @Value("${config.oauth2.scope}")
    private String scope;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(clientId)
                .secret(passwordEncoder.encode(secret))
                .authorizedGrantTypes(grantTypes)
                .scopes(scope)
                .autoApprove(true)
                .redirectUris(
                        uri1, uri2, uri3, uri4)
                .accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

}