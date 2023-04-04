package com.msa.book.adapter;

import com.msa.book.dto.ModifyStockBookDto;
import com.msa.book.service.StockBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockBookConsumer {
    private final StockBookService stockBookService;

    @KafkaListener(topics = "book-service.modify-stock-book")
    public void modifyStockBook(@Payload @Valid ModifyStockBookDto.ModifyStockBookReqDto modifyStockBookReqDto) {
        stockBookService.rentStockBook(modifyStockBookReqDto);
    }
}
