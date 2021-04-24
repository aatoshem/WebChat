package com.leidos.webchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                    .antMatchers("/", "/login**", "/css/**", "/js/**", "/wsChat/**")
                    .permitAll()
                // all other requests
                .anyRequest().authenticated()
                // Logout and redirect to the root page
                .and().logout().logoutSuccessUrl("/")
                // enable Oauth2 Login
                .and().oauth2Login();
    }

}
