package com.accountopeningapplication.entities;

import com.accountopeningapplication.enums.Action;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "transaction_history", indexes = {
        @Index(name = "idx_transaction_account_id", columnList = "account_id")
})
public class Transaction extends BaseEntity {
    private String reference;

    private String status;

    private BigDecimal amount;

    private BigDecimal balance;

    private String description;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Enumerated(value = EnumType.STRING)
    private Action action;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
