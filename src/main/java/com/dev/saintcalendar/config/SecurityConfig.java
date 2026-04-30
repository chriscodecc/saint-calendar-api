package com.dev.saintcalendar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.authorization.AuthorizationManagers.allOf;
import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
    public SecurityConfig() {
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /**http
            //Cross-Site Request Forgery
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests((authorize) -> authorize
                            .requestMatchers(HttpMethod.GET, "/api/saints/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/saints").permitAll()
                            // TODO: TEMPORARILY
                            .requestMatchers("/error").permitAll()
                            .anyRequest()
                            .authenticated());  **/      
                            
        //return http.httpBasic(withDefaults()).build();
        return  http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests((auth -> auth
                                                    .anyRequest()
                                                    .permitAll()))
                .build();
    }
}
