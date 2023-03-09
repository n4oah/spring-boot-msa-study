package com.msa.account.vo;

import com.msa.account.component.IJwtClaim;
import com.msa.account.constants.AccountRole;

import java.util.Set;

public record AccountJwtClaim(Long id, String email, String name, Set<AccountRole> roles) implements IJwtClaim {}
