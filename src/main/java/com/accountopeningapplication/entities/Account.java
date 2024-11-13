package com.accountopeningapplication.entities;

import com.accountopeningapplication.enums.AccountType;
import com.accountopeningapplication.enums.Tierlevel;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "account", indexes = {
        @Index(name = "idx_account_customer_id", columnList = "customer_id")
})
public class Account extends BaseEntity {

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_closed")
    private boolean isClosed;

    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "tier_id")
    @Enumerated(EnumType.STRING)
    private Tierlevel accountTier;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();


    public void addTransactions(Transaction transaction) {
        transactions.add(transaction);
        transaction.setAccount(this);
    }

}
