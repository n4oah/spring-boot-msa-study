package com.msa.gateway.adaptor.account;

import com.msa.gateway.adaptor.account.dto.AccountAuthJwtDecodeDto;
import com.msa.gateway.vo.AccountJwtClaim;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component()
@RequiredArgsConstructor
public class AccountServiceClient {
    @Qualifier("accountServiceWebClient")
    private final WebClient.Builder accountServiceWebClient;

    public Mono<AccountJwtClaim>
        authJwtDecodeToAccount(AccountAuthJwtDecodeDto.AccountAuthJwtDecodeReqDto authJwtDecodeReqDto) {
        return accountServiceWebClient
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/auth/jwt/decode").queryParam("accessToken", authJwtDecodeReqDto.accessToken()).build())
                .retrieve()
                .bodyToMono(AccountJwtClaim.class);
    }
}
