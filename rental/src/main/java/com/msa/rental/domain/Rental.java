package com.msa.rental.domain;

import com.msa.rental.adapter.StockBookClient;
import com.msa.rental.constants.BookStatus;
import com.msa.rental.dto.GetStockBookDetailDto;
import com.msa.rental.exception.NotRentalBookException;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Rental extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false)
    Long accountId;

    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<RentedBook> rentedBooks = new HashSet<>();

    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ReturnedBook> returnedBooks = new HashSet<>();

    public Rental(Long accountId) {
        this.accountId = accountId;
    }

    protected Rental() {

    }

    public void addRentalBook(Long stockBookId, StockBookClient stockBookClient) {
        final GetStockBookDetailDto.GetStockBookDetailResDto stockBookDetail
                = stockBookClient.getStockBookDetail(stockBookId).getBody();

        if (stockBookDetail.status() != BookStatus.Available) {
            throw new NotRentalBookException();
        }

        final RentedBook rentedBookBook = new RentedBook();
        final StockBook stockBook = new StockBook();

        stockBook.setStockBookId(stockBookDetail.stockBookId());
        stockBook.setStockBookTitle(stockBookDetail.book().title());

        rentedBookBook.setStockBook(stockBook);
        rentedBookBook.setExpectedReturnDate(LocalDate.now().plus(7, ChronoUnit.DAYS));
        rentedBookBook.setRental(this);

        this.rentedBooks.add(rentedBookBook);
    }
}
