package com.msa.account.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.ToString;

@Entity()
@ToString()
public class AccountAuthRole {
    @Id
    @Size(max = 30, min = 30)
    @Column(nullable = false, name = "name", length = 30)
    private String name;

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
