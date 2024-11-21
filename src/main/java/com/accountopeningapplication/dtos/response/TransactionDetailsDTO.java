package com.accountopeningapplication.dtos.response;

import com.accountopeningapplication.enums.Action;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetailsDTO {
    private String reference;
    private String status;
    private BigDecimal amount;
    private BigDecimal balance;
    private String description;
    private String customerId;
    private Action action;
    private String accountId;
}
