package com.isai.demo_spring_security_example.config;

import java.util.Arrays;
import java.util.List;

import com.isai.demo_spring_security_example.DemoSpringSecurityExampleApplication;
import com.isai.demo_spring_security_example.service.UserDetailServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /*
     * @Bean
     * public SecurityFilterChain securityFilterChain(HttpSecurity hSecurity) throws
     * Exception {
     * return hSecurity
     * .csrf(csrf -> csrf.disable())
     * .httpBasic(Customizer.withDefaults())
     * .sessionManagement(sesion ->
     * sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
     * .authorizeHttpRequests(http -> {
     * // configuras los endpoints publicos
     * http.requestMatchers(HttpMethod.GET, "/api/v1/auth/hola").permitAll();
     * // configuras los endpoint privados
     * http.requestMatchers(HttpMethod.GET,
     * "/api/v1/auth/hola-seguro").hasAnyAuthority("CREATED");
     * // configurar el resto de endpoint no especificados
     * http.anyRequest().denyAll();
     * })
     * .build();
     * }
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity hSecurity) throws Exception {
        return hSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sesion -> sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl detailServiceImpl) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(detailServiceImpl);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> usersDetails = Arrays.asList(
                User.withUsername("jhona")
                        .password("jhona")
                        .roles("ADMIN")
                        .authorities("READ", "CREATED")
                        .build(),
                User.withUsername("daniel")
                        .password("daniel")
                        .roles("ADMIN")
                        .authorities("READ")
                        .build());
        return new InMemoryUserDetailsManager(usersDetails);
    }
}
