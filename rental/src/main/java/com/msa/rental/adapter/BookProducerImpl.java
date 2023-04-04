package com.msa.rental.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.rental.dto.ModifyStockBookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookProducerImpl implements BookProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final String bookTopic = "book-service";

    @Override
    public void modifyStockBook(ModifyStockBookDto.ModifyStockBookReqDto modifyStockBookReqDto) {
        try {
            String message = objectMapper.writeValueAsString(modifyStockBookReqDto);
            this.kafkaTemplate.send(this.bookTopic + "." + "modify-stock-book", message);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException();
        }
    }
}
