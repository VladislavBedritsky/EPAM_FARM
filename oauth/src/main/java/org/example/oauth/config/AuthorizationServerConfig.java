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
    private String PRIVATE_KEY;

    @Value("${config.oauth2.publicKey}")
    private String PUBLIC_KEY;

    @Value("${config.oauth2.redirectUri1}")
    private String URI_1;

    @Value("${config.oauth2.redirectUri2}")
    private String URI_2;

    @Value("${config.oauth2.redirectUri3}")
    private String URI_3;

    @Value("${config.oauth2.clientId}")
    private String CLIENT_ID;

    @Value("${config.oauth2.secret}")
    private String SECRET;

    @Value("${config.oauth2.grantTypes}")
    private String GRANT_TYPES;

    @Value("${config.oauth2.scope}")
    private String SCOPE;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(CLIENT_ID)
                .secret(passwordEncoder.encode(SECRET))
                .authorizedGrantTypes(GRANT_TYPES)
                .scopes(SCOPE)
                .autoApprove(true)
                .redirectUris(
                        URI_1,URI_2,URI_3)
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
        converter.setSigningKey(PRIVATE_KEY);
        converter.setVerifierKey(PUBLIC_KEY);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

}