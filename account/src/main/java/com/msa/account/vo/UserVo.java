package com.msa.account.vo;

import com.msa.account.constants.AccountRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

@Getter
public class UserVo extends User {
    private final Long id;
    private final String name;

    public UserVo(Long id, String email, String password, String name, Set<AccountRole> accountRoles) {
        super(email, password, accountRoles);

        this.id = id;
        this.name = name;
    }
}
