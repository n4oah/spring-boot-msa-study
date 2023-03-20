package com.msa.book.dto;

import com.msa.book.constants.BookLibraryLocation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateStockBookDto {
    public record CreateStockBookReqDto(
            @NotNull
            @Positive
            Long bookId,
            @NotNull
            BookLibraryLocation libraryLocation
    ) {
    }
}
