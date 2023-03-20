package com.msa.book.repository;

import com.msa.book.domain.StockBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockBookRepository extends JpaRepository<StockBook, Long> {
}
