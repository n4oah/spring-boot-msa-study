package com.msa.book.service;

import com.msa.book.domain.Book;
import com.msa.book.dto.CreateBookDto;
import com.msa.book.dto.GetBookDetailDto;
import com.msa.book.exception.NotFoundBookException;
import com.msa.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    public void createBook(CreateBookDto.CreateBookReqDto createBookReqDto) {
        final Book book = new Book(
                createBookReqDto.title(),
                createBookReqDto.author(),
                createBookReqDto.description(),
                createBookReqDto.publisher(),
                createBookReqDto.publicationDate(),
                createBookReqDto.bookClassification()
        );

        this.bookRepository.save(book);
    }

    public GetBookDetailDto.GetBookDetailResDto getBookDetail(Long bookId) {
        GetBookDetailDto.GetBookDetailResDto getBookDetailResDto =
                GetBookDetailDto.GetBookDetailResDto.fromEntity(this.bookRepository.findById(bookId).orElseThrow(() -> new NotFoundBookException()));

        return getBookDetailResDto;
    }

    public void existsBookOrError(Long bookId) {
        if (!this.bookRepository.existsById(bookId)) {
            throw new NotFoundBookException();
        }
    }
}
