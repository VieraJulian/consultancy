package com.consultancy.users.infrastructure.security.config;

import com.consultancy.users.application.utils.JwtUtils;
import com.consultancy.users.infrastructure.security.filter.JwtTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtils jwtUtils;

    @Autowired
    public SecurityConfig(JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(HttpMethod.GET, "/users/{id}").authenticated();
                    authorize.requestMatchers(HttpMethod.POST, "/users/create").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT, "/users/update/{id}").authenticated();
                    authorize.requestMatchers(HttpMethod.GET, "/users").authenticated();
                    authorize.requestMatchers(HttpMethod.DELETE, "/users/delete/{id}").hasRole("ADMIN");

                    authorize.requestMatchers(HttpMethod.POST, "/roles/create").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT, "/roles/update/{id}").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.GET, "/roles/all").authenticated();
                    authorize.requestMatchers(HttpMethod.GET, "/roles/{id}").authenticated();
                    authorize.requestMatchers(HttpMethod.DELETE, "/roles/delete/{id}").hasRole("ADMIN");

                    authorize.requestMatchers(HttpMethod.POST, "/permissions/create").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.GET, "/permissions/{id}").authenticated();
                    authorize.requestMatchers(HttpMethod.GET, "/permissions/all").authenticated();
                    authorize.requestMatchers(HttpMethod.DELETE, "/permissions/delete/{id}").hasRole("ADMIN");

                    authorize.requestMatchers("/auth/**").permitAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
