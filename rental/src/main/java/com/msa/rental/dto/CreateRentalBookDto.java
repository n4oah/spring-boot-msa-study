package com.msa.rental.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateRentalBookDto {
    public record CreateRentalBookReqDto(
            @NotNull(message = "stockBookId의 값은 필수 입니다.")
            @Positive(message = "stockBookId의 값은 양수이어야 합니다.")
            Long stockBookId
    ) {
    }
}
