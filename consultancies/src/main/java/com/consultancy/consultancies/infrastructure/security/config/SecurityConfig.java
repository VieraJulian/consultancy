package com.consultancy.consultancies.infrastructure.security.config;

import com.consultancy.consultancies.application.utils.JwtUtils;
import com.consultancy.consultancies.infrastructure.security.filter.JwtTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtils jwtUtils;

    @Autowired
    public SecurityConfig(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(HttpMethod.POST, "/professionals/create").authenticated();
                    authorize.requestMatchers(HttpMethod.PUT, "/professionals/update/**").authenticated();
                    authorize.requestMatchers(HttpMethod.GET, "/professionals/all/**").authenticated();
                    authorize.requestMatchers(HttpMethod.GET, "/professionals/{id}").authenticated();
                    authorize.requestMatchers(HttpMethod.DELETE, "/professionals/delete/**").hasRole("ADMIN");

                    authorize.requestMatchers(HttpMethod.POST, "/availability/add/{professionalId}").authenticated();
                    authorize.requestMatchers(HttpMethod.PUT, "/availability/update/{id}").authenticated();
                    authorize.requestMatchers(HttpMethod.DELETE, "/availability/delete/{id}").hasRole("ADMIN");
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class);

        return http.build();
    }
}
