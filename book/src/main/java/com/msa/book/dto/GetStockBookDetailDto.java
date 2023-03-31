package com.msa.book.dto;

import com.msa.book.constants.BookLibraryLocation;
import com.msa.book.constants.BookStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class GetStockBookDetailDto {
    public record GetStockBookDetailResDto(Long stockBookId, BookLibraryLocation libraryLocation, BookStatus status,
                                           GetBookDetailDto.GetBookDetailResDto book) {
    }
}
