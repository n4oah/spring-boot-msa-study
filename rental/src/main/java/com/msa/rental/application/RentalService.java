package com.msa.rental.application;

import com.msa.rental.adapter.StockBookClient;
import com.msa.rental.domain.Rental;
import com.msa.rental.dto.CreateRentalBookDto;
import com.msa.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final StockBookClient stockBookClient;

    public void createRentalBook(
            Long accountId,
            CreateRentalBookDto.CreateRentalBookReqDto createRentalBookReqDto
    ) {
        final Rental rental = rentalRepository.findByAccountId(accountId).orElse(new Rental(accountId));
        rental.addRentalBook(createRentalBookReqDto.stockBookId(), stockBookClient);

        this.rentalRepository.save(rental);
    }
}
