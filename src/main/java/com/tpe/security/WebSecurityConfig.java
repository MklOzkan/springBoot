package com.tpe.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
            1. Authentication Manager
            2. Authentication Provider
            3. Encode Password
            4. UserDetailService
     */

    //configure Http requests
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().//Disable Cross Site Request Forgery
                authorizeHttpRequests(). //authorize all http requests
                antMatchers("/", "index.html", "/css/*", "/js/*", "/register").permitAll(). //some requests should be permitted
                anyRequest(). //all other requests
                authenticated(). //should be authenticated - we need to check username and password
                and().
                httpBasic(); //security type we are using is basic authentication
    }
}
