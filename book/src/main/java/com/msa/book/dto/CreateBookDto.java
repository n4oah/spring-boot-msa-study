package com.msa.book.dto;

import com.msa.book.constants.BookClassification;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CreateBookDto {
    public record CreateBookReqDto(
            @NotNull
            @NotEmpty
            String title,
            @NotNull()
            @NotEmpty()
            String author,
            @NotNull()
            @NotEmpty()
            String description,
            @NotNull()
            @NotEmpty()
            String publisher,
            @NotNull()
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate publicationDate,
            @NotNull()
            BookClassification bookClassification
    ) {

    }
}
