package com.msa.rental.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class StockBook {
    @Column(name="stock_book_id", nullable = false)
    private Long stockBookId;

    @Column(name="stock_book_title", nullable = false)
    private String stockBookTitle;

    protected StockBook() {

    }
}
