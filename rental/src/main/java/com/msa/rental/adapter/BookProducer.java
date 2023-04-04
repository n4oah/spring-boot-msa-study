package com.msa.rental.adapter;

import com.msa.rental.dto.ModifyStockBookDto;

public interface BookProducer {
    void modifyStockBook(ModifyStockBookDto.ModifyStockBookReqDto modifyStockBookReqDto);
}
