package com.lgda.backend.config;

import com.lgda.backend.filter.JwtAuthenticationFilter;
import com.lgda.backend.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler)
                .and()

                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**").permitAll() /* n'importe qui a accès à cet url */
                .requestMatchers("/api/v1/demo/users-only").hasAnyRole(Role.USER.name()) /* ROLE_USER */
                .requestMatchers("/api/v1/demo/admin-only").hasAnyRole(Role.ADMIN.name()) /* ROLE_ADMIN */
//                .requestMatchers("/categories").hasAnyRole(Role.ADMIN.name()) /* ROLE_ADMIN */
                .requestMatchers("/categories").hasAnyRole(Role.ADMIN.name()) /* ROLE_ADMIN */
                .requestMatchers("/products").hasAnyRole(Role.ADMIN.name()) /* ROLE_ADMIN */
                .requestMatchers("/orders/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name()) /* ROLE_ADMIN */
//TODO: Ajouter les routes supplémentaires pour les CRUD
                .anyRequest().authenticated()
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();

    }
}
