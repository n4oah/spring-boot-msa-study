package com.msa.book.service;

import com.msa.book.domain.Book;
import com.msa.book.dto.CreateBookDto;
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
}
