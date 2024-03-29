package com.msa.book.domain;

import com.msa.book.constants.BookLibraryLocation;
import com.msa.book.constants.BookStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Entity()
@Table(name = "stock_book")
@Getter
public class StockBook extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "library_location", nullable = false)
    private BookLibraryLocation libraryLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookStatus status;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    protected StockBook() {
    }

    public StockBook(Long bookId, BookLibraryLocation libraryLocation, BookStatus status) {
        this.bookId = bookId;
        this.libraryLocation = libraryLocation;
        this.status = status;
    }

    public void rentStockBook() {
        if (this.status == BookStatus.Rent) {
            throw new RuntimeException("빌릴 수 없는 책입니다.");
        }

        this.status = BookStatus.Rent;
    }

    public void returnStockBook() {
        if (this.status == BookStatus.Available) {
            throw new RuntimeException("이미 반납된 책 입니다.");
        }

        this.status = BookStatus.Available;
    }

    public void setLibraryLocation(BookLibraryLocation libraryLocation) {
        this.libraryLocation = libraryLocation;
    }
}
