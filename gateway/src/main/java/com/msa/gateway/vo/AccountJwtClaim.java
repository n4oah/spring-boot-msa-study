package com.msa.gateway.vo;

import com.msa.gateway.constants.AccountRole;

import java.util.Set;

public record AccountJwtClaim(Long id, String email, String name, Set<AccountRole> roles) {}
