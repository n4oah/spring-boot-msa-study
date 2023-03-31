package com.msa.book.service;

import com.msa.book.constants.BookStatus;
import com.msa.book.domain.Book;
import com.msa.book.domain.StockBook;
import com.msa.book.dto.CreateStockBookDto;
import com.msa.book.dto.GetBookDetailDto;
import com.msa.book.dto.GetStockBookDetailDto;
import com.msa.book.exception.NotFoundStockBookException;
import com.msa.book.repository.StockBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockBookService {
    private final StockBookRepository stockBookRepository;
    private final BookService bookService;

    public void createStockBook(CreateStockBookDto.CreateStockBookReqDto createStockBookReqDto) {
        this.bookService.existsBookOrError(createStockBookReqDto.bookId());

        final StockBook stockBook = new StockBook(
                createStockBookReqDto.bookId(),
                createStockBookReqDto.libraryLocation(),
                BookStatus.Available
        );

        stockBookRepository.save(stockBook);
    }

    public GetStockBookDetailDto.GetStockBookDetailResDto getStockBookDetail(Long stockBookId) {
        final StockBook stockBook = this.stockBookRepository.findById(stockBookId).orElseThrow(NotFoundStockBookException::new);
        final GetBookDetailDto.GetBookDetailResDto book = bookService.getBookDetail(stockBook.getBookId());

        return new GetStockBookDetailDto.GetStockBookDetailResDto(
                stockBook.getId(), stockBook.getLibraryLocation(), stockBook.getStatus(),
                book
        );
    }
}
