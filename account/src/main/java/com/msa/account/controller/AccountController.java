package com.msa.account.controller;

import com.msa.account.dto.AuthJwtDecodeDto;
import com.msa.account.dto.SigninDto;
import com.msa.account.dto.SignupDto;
import com.msa.account.service.AccountService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/signin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(
            @ApiResponse(responseCode = "200", content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation=SigninDto.SigninDtoRes.class)))
    )
    public void signin(@Valid() @RequestBody(required = true) final SigninDto.SigninDtoReq signinDtoReq) {}

    /*
     * 이거 왜 만들어 놨지..?
     */
    @PostMapping("/login")
    public void signin1(@Valid() @RequestBody(required = true) final SigninDto.SigninDtoReq signinDtoReq) {}

    @GetMapping("/auth/jwt/decode")
    public AuthJwtDecodeDto.AuthJwtDecodeResDto jwtDecode(@Valid() AuthJwtDecodeDto.AuthJwtDecodeReqDto authJwtDecodeReqDto) {
        return this.accountService.jwtDecode(authJwtDecodeReqDto.getAccessToken());
    }
}
