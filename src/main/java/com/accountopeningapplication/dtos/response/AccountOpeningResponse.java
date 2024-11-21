package com.accountopeningapplication.dtos.response;

import com.accountopeningapplication.enums.AccountType;
import com.accountopeningapplication.enums.Tierlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountOpeningResponse {

    private String accountNumber;
    private boolean isActive;
    private boolean isClosed;
    private String accountId;
    private BigDecimal balance;
    private String userFriendlyMessage;
    private Tierlevel accountTier;
    private AccountType accountType;
    private String customerId;
}
