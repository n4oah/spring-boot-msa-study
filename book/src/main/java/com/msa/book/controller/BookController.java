package com.msa.book.controller;

import com.msa.book.dto.CreateBookDto;
import com.msa.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/")
@RequiredArgsConstructor()
public class BookController {
    private final BookService bookService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@Valid() @RequestBody() CreateBookDto.CreateBookReqDto createBookReqDto) {
        bookService.createBook(createBookReqDto);
    }
}
