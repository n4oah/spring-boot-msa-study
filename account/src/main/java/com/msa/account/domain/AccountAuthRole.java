package com.msa.account.domain;

import com.msa.account.constants.AccountRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity()
@ToString()
@Getter()
public class AccountAuthRole {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "name")
    private AccountRole name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountAuthRole)) {
            return false;
        }

        return this.name.equals(((AccountAuthRole) o).name);
    }
}
