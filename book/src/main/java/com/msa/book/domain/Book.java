package com.msa.book.domain;

import com.msa.book.constants.BookClassification;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Book extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "classification", nullable = false)
    private BookClassification classification;

    @OneToMany(mappedBy = "book")
    private List<InStockBook> inStockBooks = new ArrayList<>();
}
