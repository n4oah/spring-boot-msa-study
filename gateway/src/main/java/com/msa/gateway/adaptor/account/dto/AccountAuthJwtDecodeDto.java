package com.msa.gateway.adaptor.account.dto;

import com.msa.gateway.vo.AccountJwtClaim;

public class AccountAuthJwtDecodeDto {
    public record AccountAuthJwtDecodeReqDto(String accessToken) {}
    public record AccountAuthJwtDecodeResDto(AccountJwtClaim account) {}
}
