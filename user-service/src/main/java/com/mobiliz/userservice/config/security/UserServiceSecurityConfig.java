package com.mobiliz.userservice.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserServiceSecurityConfig {

    @Bean
    JwtTokenFilter getJwtTokenFilter(){
        return  new JwtTokenFilter();
    }


    private static final String[]  WHITELIST ={
            "/swagger-ui/**","/v3/api-docs/**","/user/login"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests()
                .antMatchers(WHITELIST)
                .permitAll()
                .anyRequest().authenticated();
        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        httpSecurity.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return  httpSecurity.build();

    }
}
