package com.task.theeducationalinstitute.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {

    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    public SecurityFilterChainConfig(AuthenticationEntryPoint authenticationEntryPoint, JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //disable CORS
        httpSecurity.cors(AbstractHttpConfigurer::disable);


        //Disable CSRF
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        //Http Request Filter
        httpSecurity.authorizeHttpRequests(
                requestMatcher ->
                        requestMatcher.requestMatchers("/api/{groupId}/total-groupworkload").permitAll()
                                .requestMatchers("/api/{teacherId}/total-workload").permitAll()
                                .anyRequest().authenticated()
        );

        // Authentication Entry Point -> Exception Handler
        httpSecurity.exceptionHandling(
                exceptionConfig-> exceptionConfig.authenticationEntryPoint(authenticationEntryPoint)
        );

        // Set session policy = STATELESS
        httpSecurity.sessionManagement(
                sessionConfig-> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        //Add JWT Authentication Filter
        httpSecurity.addFilterBefore(jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }
}
