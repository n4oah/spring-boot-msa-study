package com.msa.account.dto;

import com.msa.account.domain.Account;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;


public class SigninDto {
    public record SigninDtoReq(
            @NotNull
            @NotEmpty
            String userId,
            @NotEmpty
            @NotNull
            String password
    ) {
    }

    public record SigninDtoRes(
            @NotNull
            @NotEmpty
            String accessToken
    ) {
    }
}
