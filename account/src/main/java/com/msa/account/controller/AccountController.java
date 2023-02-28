package com.msa.account.controller;

import com.msa.account.dto.SignupDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/")
public class AccountController {
    @PostMapping("/signup")
    public void signup(@Valid() @RequestBody(required = true) final SignupDto.SignupDtoReq signupDtoReq) {

        System.out.println("signupDtoReq" + signupDtoReq);

    }
}
