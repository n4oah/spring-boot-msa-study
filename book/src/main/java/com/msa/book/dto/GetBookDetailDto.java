package com.msa.book.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msa.book.constants.BookClassification;
import com.msa.book.domain.Book;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

public class GetBookDetailDto {
    public record GetBookDetailResDto(@NotNull @Positive Long bookId, @NotNull String title, @NotNull String author,
                                      @NotNull String description, @NotNull String publisher,
                                      @JsonFormat(pattern = "yyyy-MM-dd") LocalDate publicationDate,
                                      @NotNull BookClassification classification) {
        public static GetBookDetailResDto fromEntity(Book book) {
            return new GetBookDetailResDto(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getDescription(),
                    book.getPublisher(),
                    book.getPublicationDate(),
                    book.getClassification()
            );
        }
    }
}
