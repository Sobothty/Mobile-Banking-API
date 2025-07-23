package com.bothty.mobilebankingjpa.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    private String ROLE_ADMIN = "ADMIN";
    private String ROLE_STAFF = "STAFF";
    private String ROLE_CUSTOMER = "CUSTOMER";

    @Bean
    public InMemoryUserDetailsManager configureUser() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("qwerqwer"))
                .roles(ROLE_ADMIN)
                .build();
        manager.createUser(admin);

        UserDetails staff = User.builder()
                .username("staff")
                .password(passwordEncoder.encode("qwerqwer"))
                .roles(ROLE_STAFF)
                .build();
        manager.createUser(staff);

        UserDetails user = User.builder()
                .username("User")
                .password(passwordEncoder.encode("qwerqwer"))
                .roles(ROLE_CUSTOMER)
                .build();

        manager.createUser(user);

        return manager;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoProvider =
                new DaoAuthenticationProvider(userDetailsService);
        daoProvider.setPasswordEncoder(passwordEncoder);

        return daoProvider;
    }



    @Bean
    public SecurityFilterChain configureApiSecurity(HttpSecurity httpSecurity) throws Exception {

        // Make all endpoint secure
        httpSecurity.authorizeHttpRequests(endpoint -> endpoint
                .requestMatchers(HttpMethod.GET,"/api/v1/customers/**").hasAnyRole(ROLE_ADMIN, ROLE_STAFF)
                .requestMatchers(HttpMethod.POST,"/api/v1/customers/**").hasAnyRole(ROLE_ADMIN, ROLE_STAFF)
                .requestMatchers(HttpMethod.PUT,"/api/v1/customers/**").hasAnyRole(ROLE_ADMIN, ROLE_STAFF)
                .requestMatchers(HttpMethod.DELETE,"/api/v1/customers/**").hasAnyRole(ROLE_ADMIN)
                .requestMatchers("/api/v1/accounts/**").hasAnyRole(ROLE_ADMIN, ROLE_STAFF, ROLE_CUSTOMER)
                .anyRequest()
                .permitAll()
        );

        // Disable form login of web
        httpSecurity.formLogin(AbstractHttpConfigurer::disable);

        //Accept any form
        httpSecurity.httpBasic(Customizer.withDefaults());

        // CSRF common protection -> CSRF generate token
        httpSecurity.csrf(csrf -> csrf.disable());

        // Set Security mechanism
        httpSecurity.httpBasic(Customizer.withDefaults());

        // Make api stateless
        httpSecurity.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return httpSecurity.build();
    }
}
