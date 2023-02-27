package com.msa.account.config;

import com.msa.account.constants.AccountRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
        final AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        System.out.println("AA  " + http.getSharedObjects());

        System.out.println("authenticationManager  :  " + authenticationManager);

        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/signin").permitAll()
                        .requestMatchers("/signup").permitAll()
                        .anyRequest().hasRole(AccountRole.USER.getRole()))
                .addFilter(this.getAuthenticationFilter(this.authenticationManager(authenticationConfiguration)))
                .formLogin().disable()
        ;
        return http.build();
    }

    private UsernamePasswordAuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        UsernamePasswordAuthenticationFilter authenticationFilter = new UsernamePasswordAuthenticationFilter(authenticationManager);
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/signin",
                HttpMethod.POST.name()));
        authenticationFilter.setAuthenticationSuccessHandler(((request, response, authResult) -> {
            final Object a = authResult.getPrincipal();

            System.out.println("a" + a);
        }));

        return authenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
