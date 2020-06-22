package org.example.epam.webapp.oAuth2;

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

    private static final String ACCESS_TOKEN_URI = "http://3.121.199.219:8981/auth/oauth/token";
    private static final String USER_AUTH_URI = "http://3.121.199.219:8981/auth/oauth/authorize";

    @Value("${security.oauth2.client.clientId}")
    private String clientId;
    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @Resource
    private OAuth2ClientContext oAuth2ClientContext;

    @Bean
    public OAuth2ProtectedResourceDetails resourceDetails() {
        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        resource.setClientId(clientId);
        resource.setClientSecret(clientSecret);
        resource.setAccessTokenUri(ACCESS_TOKEN_URI);
        resource.setUserAuthorizationUri(USER_AUTH_URI);

        return resource;
    }

    @Bean
    public OAuth2RestOperations oAuth2RestTemplate() {
        return new OAuth2RestTemplate(resourceDetails(), oAuth2ClientContext);
    }

}
