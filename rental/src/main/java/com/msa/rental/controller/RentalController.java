package com.msa.rental.controller;

import com.msa.rental.dto.CreateRentalBookDto;
import com.msa.rental.application.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void rentalBook(
        @RequestHeader("X-auth-account-id") Long accountId,
        @Valid @RequestBody CreateRentalBookDto.CreateRentalBookReqDto createRentalBookReqDto
    ) {
        System.out.println("accountId" + accountId + "zz" + createRentalBookReqDto.stockBookId() );
        this.rentalService.createRentalBook(accountId, createRentalBookReqDto);
    }
}
