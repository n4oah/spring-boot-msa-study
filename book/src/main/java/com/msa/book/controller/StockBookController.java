package com.msa.book.controller;

import com.msa.book.dto.CreateStockBookDto;
import com.msa.book.service.StockBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockBookController {
    private final StockBookService stockBookService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createStockBook(@RequestBody @Valid CreateStockBookDto.CreateStockBookReqDto createStockBookReqDto) {
        this.stockBookService.createStockBook(createStockBookReqDto);
    }
}
