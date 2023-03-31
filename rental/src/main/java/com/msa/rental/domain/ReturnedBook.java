package com.msa.rental.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Entity()
@Table(name = "returned_book")
@Data
public class ReturnedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private StockBook stockBook;

    @Column(name = "rental_date", nullable = false)
    private LocalDate rentalDate;

    @JoinColumn(nullable = false, name = "rental_id")
    @ManyToOne()
    private Rental rental;
}
