package com.msa.account.dto;

import com.msa.account.constants.AccountRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class AuthJwtDecodeDto {
    @RequiredArgsConstructor
    @Data()
    public static class AuthJwtDecodeReqDto {
        @NotNull() @NotEmpty() private final String accessToken;
    }

    public record AuthJwtDecodeResDto(

            @NotNull
            @NotEmpty
            Long id,
            @NotNull()
            @NotEmpty()
            String userId,
            @NotNull()
            @NotEmpty()
            String name,
            @NotNull()
            List<AccountRole> roles
    ) {}
}
