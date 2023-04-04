package com.msa.rental.dto;

import com.msa.rental.constants.BookLibraryLocation;
import com.msa.rental.constants.BookStatus;
import lombok.*;

public class ModifyStockBookDto {
    @Builder(builderMethodName = "hiddenBuilder")
    @Getter
    @ToString
    public static class ModifyStockBookReqDto {
        private final Long stockBookId;
        private BookLibraryLocation libraryLocation;
        private BookStatus status;

        public static ModifyStockBookReqDtoBuilder builder(Long stockBookId) {
            return hiddenBuilder().stockBookId(stockBookId);
        }
    }
}
