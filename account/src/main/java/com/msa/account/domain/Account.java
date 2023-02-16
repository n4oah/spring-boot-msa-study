package com.msa.account.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@Builder
@Data
public class Account extends AbstractAuditingEntity {
    public Account() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull()
    @Size(min=4, max=50)
    @Column(name = "user_id", length = 50, nullable = false, unique = true)
    private String userId;

    @NotNull()
    @Size(min=128, max=128)
    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @NotNull()
    @Size(min = 30, max = 30)
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "account_auth",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")})
    private Set<AccountAuthRole> authorities = new HashSet<>();
}
