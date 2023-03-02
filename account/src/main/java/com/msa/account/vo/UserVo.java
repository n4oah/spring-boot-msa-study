package com.msa.account.vo;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class UserVo extends User {
    private String name;

    public UserVo(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserVo(String username, String password, Collection<? extends GrantedAuthority> authorities, String name) {
        this(username, password, authorities);
        this.name = name;
    }
}
