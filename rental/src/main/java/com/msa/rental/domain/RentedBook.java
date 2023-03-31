package com.msa.rental.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "rented_book")
@Data
public class RentedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private StockBook stockBook;

    @Column(name = "expected_return_date", nullable = false)
    private LocalDate expectedReturnDate;

    @JoinColumn(nullable = false, name = "rental_id")
    @ManyToOne()
    private Rental rental;
}
