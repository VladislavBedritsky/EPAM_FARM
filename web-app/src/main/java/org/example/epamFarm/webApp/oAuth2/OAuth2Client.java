package org.example.epamFarm.webApp.oAuth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import javax.annotation.Resource;

@Configuration
@EnableOAuth2Client
public class OAuth2Client {

    @Value("${security.oauth2.client.clientId}")
    private String CLIENT_ID;
    @Value("${security.oauth2.client.clientSecret}")
    private String CLIENT_SECRET;
    @Value("${security.oauth2.client.accessTokenUri}")
    private String ACCESS_TOKEN_URI;
    @Value("${security.oauth2.client.userAuthorizationUri}")
    private String USER_AUTHORIZATION_URI;

    @Resource
    private OAuth2ClientContext oAuth2ClientContext;

    @Bean
    public OAuth2ProtectedResourceDetails resourceDetails() {
        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        resource.setClientId(CLIENT_ID);
        resource.setClientSecret(CLIENT_SECRET);
        resource.setAccessTokenUri(ACCESS_TOKEN_URI);
        resource.setUserAuthorizationUri(USER_AUTHORIZATION_URI);

        return resource;
    }

    @Bean
    public OAuth2RestOperations oAuth2RestTemplate() {
        return new OAuth2RestTemplate(resourceDetails(),oAuth2ClientContext);
    }

}
