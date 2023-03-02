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
    @Size(min=60, max=60)
    @Column(name = "password", length = 60, nullable = false, columnDefinition = "CHAR(60)")
    private String password;

    @NotNull()
    @Size(min = 2, max = 5)
    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "account_auth",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")})
    private Set<AccountAuthRole> authorities = new HashSet<>();
}
