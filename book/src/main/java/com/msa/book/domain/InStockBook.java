package com.msa.book.domain;

import com.msa.book.constants.BookLibraryLocation;
import com.msa.book.constants.BookStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Entity()
@Table(name = "in_stock_book")
@Getter
public class InStockBook extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "library_location", nullable = false)
    private BookLibraryLocation libraryLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookStatus status;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "book_id")
    private Book book;
}
