package com.msa.gateway.filter;

import com.msa.gateway.adaptor.account.AccountServiceClient;
import com.msa.gateway.adaptor.account.dto.AccountAuthJwtDecodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    private final AccountServiceClient accountServiceClient;

    public AuthFilter(AccountServiceClient accountServiceClient) {
        super(Config.class);
        this.accountServiceClient = accountServiceClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            final String authorization = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            if (!hasAccessToken(authorization)) {
                return Mono.error(new HttpClientErrorException(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase()));
            }

            final String accessToken = authorization.replace("Bearer ", "");

            return
                    this.accountServiceClient.authJwtDecodeToAccount(new AccountAuthJwtDecodeDto.AccountAuthJwtDecodeReqDto(accessToken))
                            .map((accountAuthJwtDecodeResDto -> {
                                exchange.getRequest()
                                        .mutate()
                                        .header("X-auth-member-id", String.valueOf(accountAuthJwtDecodeResDto.id()));
                                return exchange;
                            }))
                            .flatMap(chain::filter)
                            .onErrorResume(WebClientResponseException.class, ((tt) -> {
                                byte[] bytes = tt.getResponseBodyAsByteArray();
                                exchange.getResponse().getHeaders().add("content-type", "application/json");
                                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                                exchange.getResponse().setStatusCode(tt.getStatusCode());
                                return exchange.getResponse().writeWith(Mono.just(buffer));
                            }));
        });
    }

    private boolean hasAccessToken(String authorization) {
        if (authorization == null || !StringUtils.hasText(authorization) || !authorization.startsWith("Bearer")) {
            return false;
        }

        return true;
    }

    public static class Config {

    }
}
