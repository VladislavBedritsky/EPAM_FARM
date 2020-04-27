package org.example.epam.webapp.oAuth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2Config extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2RestOperations oAuth2RestTemplate;
    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    /**
     *
     *     @Value("${security.oauth2.resource.userInfoUri}")
     *     private String userInfoUri;
     *
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .addFilterAfter(oAuth2ClientContextFilter(), AbstractPreAuthenticatedProcessingFilter.class)
                .addFilterAfter(filter(), OAuth2ClientContextFilter.class)
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/","/login**","/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID");
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Bean
    public OAuth2ClientContextFilter oAuth2ClientContextFilter() {
        return new OAuth2ClientContextFilter();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/login");
    }

    @Bean
    public OAuth2ClientAuthenticationProcessingFilter filter() {

        OAuth2ClientAuthenticationProcessingFilter oAuth2Filter = new OAuth2ClientAuthenticationProcessingFilter("/login");

        oAuth2Filter.setRestTemplate(oAuth2RestTemplate);

        oAuth2Filter.setTokenServices(new UserInfoTokenServices(
                "http://3.121.199.219:8981/auth/rest/user", clientId));

        return oAuth2Filter;
    }
}
