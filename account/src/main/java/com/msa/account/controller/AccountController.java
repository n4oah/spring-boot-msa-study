package com.msa.account.controller;

import com.msa.account.dto.SignupDto;
import com.msa.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/signup")
    public void signup(@Valid() @RequestBody(required = true) final SignupDto.SignupDtoReq signupDtoReq) {
        this.accountService.signup(signupDtoReq);
    }
}
