package com.msa.book.domain;

import com.msa.book.constants.BookClassification;
import com.msa.book.constants.BookLibraryLocation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Book(String title,
        String author,
        String description,
        String publisher,
        LocalDate publicationDate,
        BookClassification classification) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.classification = classification;
    }
}
