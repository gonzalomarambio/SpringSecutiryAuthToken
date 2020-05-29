package com.gonkables.authtoken.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthenticationEntryPoint userAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

            .addFilterBefore(new UserAuthenticationFilter(), BasicAuthenticationFilter.class)
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
            .anyRequest().authenticated()
            .and().httpBasic().authenticationEntryPoint(userAuthenticationEntryPoint);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
