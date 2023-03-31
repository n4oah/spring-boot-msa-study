package com.msa.rental.adapter;

import com.msa.rental.dto.GetStockBookDetailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="book")
public interface StockBookClient {
    @GetMapping("/stock/{stockBookId}")
    ResponseEntity<GetStockBookDetailDto.GetStockBookDetailResDto> getStockBookDetail(
            @PathVariable("stockBookId") Long stockBookId
    );
}
