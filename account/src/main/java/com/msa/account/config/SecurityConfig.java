package com.msa.account.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.account.component.JwtTokenProvider;
import com.msa.account.constants.AccountRole;
import com.msa.account.dto.SigninDto;
import com.msa.account.vo.AccountJwtClaim;
import com.msa.account.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
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

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/swagger*/**")
                .requestMatchers("/api-docs*/**");
    }

    private UsernamePasswordAuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        UsernamePasswordAuthenticationFilter authenticationFilter = new UsernamePasswordAuthenticationFilter(authenticationManager);
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/signin",
                HttpMethod.POST.name()));
        authenticationFilter.setUsernameParameter("userId");
        authenticationFilter.setPasswordParameter("password");

        authenticationFilter.setAuthenticationSuccessHandler(((request, response, authResult) -> {
            final UserVo user = (UserVo) authResult.getPrincipal();

            String jwtToken = this.jwtTokenProvider.createToken(
                    new AccountJwtClaim(
                            user.getUsername(),
                            user.getName(),
                            user.getAuthorities().stream().map(role -> AccountRole.valueOf(role.getAuthority())).collect(Collectors.toUnmodifiableSet())
                    )
            );

            final ObjectMapper objectMapper = new ObjectMapper();
            final SigninDto.SigninDtoRes responseData = new SigninDto.SigninDtoRes(jwtToken);

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.OK.value());
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(responseData));
        }));

        return authenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
