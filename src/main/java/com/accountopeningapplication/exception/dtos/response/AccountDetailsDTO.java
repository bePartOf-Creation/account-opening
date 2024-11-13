package com.accountopeningapplication.exception.dtos.response;

import com.accountopeningapplication.enums.AccountType;
import com.accountopeningapplication.enums.Tierlevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDetailsDTO {
    private String accountNumber;
    private boolean isActive;
    private boolean isClosed;
    private BigDecimal balance;
    private String customerId;
    private AccountType accountType;
    private Tierlevel accountTier;
    private PaginatedTransactionDTO transactions;
}
