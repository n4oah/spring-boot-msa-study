package com.msa.rental.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.msa.rental.constants.BookClassification;
import com.msa.rental.constants.BookLibraryLocation;
import com.msa.rental.constants.BookStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class GetStockBookDetailDto {

    public record GetBookDetailResDto(@NotNull @Positive Long bookId, @NotNull String title, @NotNull String author,
                                      @NotNull String description, @NotNull String publisher,
                                      @JsonFormat(pattern = "yyyy-MM-dd") LocalDate publicationDate,
                                      @NotNull BookClassification classification) {
    }

    public record GetStockBookDetailResDto(Long stockBookId, BookLibraryLocation libraryLocation, BookStatus status,
                                           GetBookDetailResDto book) {
    }
}
