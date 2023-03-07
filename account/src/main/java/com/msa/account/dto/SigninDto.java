package com.msa.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class SigninDto {
    public record SigninDtoReq(
            @NotNull
            @NotEmpty
            @Email
            String email,
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
