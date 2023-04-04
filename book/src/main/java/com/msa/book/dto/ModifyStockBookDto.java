package com.msa.book.dto;

import com.msa.book.constants.BookLibraryLocation;
import com.msa.book.constants.BookStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

public class ModifyStockBookDto {
    @NoArgsConstructor
    public static class ModifyStockBookReqDto {
        @NotNull
        @NotEmpty
        @Positive
        @Getter
        private Long stockBookId;
        @NotEmpty
        private BookLibraryLocation libraryLocation;
        @NotEmpty
        private BookStatus status;

        public Optional<BookLibraryLocation> getLibraryLocation() {
            return Optional.ofNullable(this.libraryLocation);
        }

        public Optional<BookStatus> getStatus() {
            return Optional.ofNullable(this.status);
        }
    }
}
