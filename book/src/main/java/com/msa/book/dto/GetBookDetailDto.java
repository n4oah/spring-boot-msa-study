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
    @Getter
    @RequiredArgsConstructor
    public static class GetBookDetailResDto {
        @NotNull
        @Positive
        private final Long bookId;
        @NotNull
        private final String title;
        @NotNull
        private final String author;
        @NotNull
        private final String description;
        @NotNull
        private final String publisher;
        @JsonFormat(pattern = "yyyy-mm-dd")
        private final LocalDate publicationDate;
        @NotNull
        private final BookClassification classification;

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
