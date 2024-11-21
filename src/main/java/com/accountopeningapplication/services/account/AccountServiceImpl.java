package com.accountopeningapplication.services.account;

import com.accountopeningapplication.dtos.requests.CurrentAccountDTO;
import com.accountopeningapplication.dtos.response.BaseResponse;
import com.accountopeningapplication.entities.Account;
import com.accountopeningapplication.exception.GeneralException;
import com.accountopeningapplication.exception.ResourceCreationException;
import com.accountopeningapplication.exception.ResourceNotFoundException;
import com.accountopeningapplication.repositories.AccountRepository;
import com.accountopeningapplication.repositories.CustomerRepository;
import com.accountopeningapplication.services.transaction.TransactionService;
import com.accountopeningapplication.utils.builders.Builder;
import com.accountopeningapplication.utils.nuban.Nuban;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.accountopeningapplication.enums.AccountType.CURRENT;
import static com.accountopeningapplication.enums.ResponseStatus.*;
import static com.accountopeningapplication.enums.Tierlevel.TIER_3;
import static com.accountopeningapplication.utils.builders.Builder.buildAccountResponse;
import static com.accountopeningapplication.utils.constants.StringValues.*;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceImpl implements AccountService {

    private final CustomerRepository customerRepo;
    private final Nuban nuban;
    private final TransactionService transactionService;
    private final AccountRepository accountRepo;


    @Override
    public BaseResponse openCurrentAccount(CurrentAccountDTO accountDTO) {
        log.info("In current Account Creation request -> {} ...", accountDTO);
        Account savedAccount = null;
        try {
            var foundCustomer = this.customerRepo.findById(accountDTO.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND, RECORD_NOT_FOUND.getCode()));

            var newCurrentAccount = Builder.buildCustomerAccount(foundCustomer, 0.0,
                    this.nuban.generateMerchantNuban(), TIER_3, CURRENT);

            if (accountDTO.getInitialBalance() <= 0) {
                return new BaseResponse(INVALID_REQUEST.getCode(), INVALID_REQUEST.getStatus(), INVALID_REQUEST.name(), MINIMUM_BALANCE);
            } else {
                newCurrentAccount.setBalance(BigDecimal.valueOf(accountDTO.getInitialBalance()));
                savedAccount = this.accountRepo.save(newCurrentAccount);

                var newTransaction = this.transactionService.createNewTransaction(savedAccount, foundCustomer.getId(),
                        accountDTO.getInitialBalance(), OPENING_BALANCE + accountDTO.getInitialBalance());

                savedAccount.addTransactions(newTransaction);
                foundCustomer.getAccounts().add(savedAccount);
                this.accountRepo.save(savedAccount);
            }
        } catch (ResourceCreationException exception) {
            log.debug(ACCOUNT_OPENING_ERROR, exception.getMessage());
            log.error(ACCOUNT_OPENING_ERROR, exception.getMessage());
        }

        assert savedAccount != null;
        return new BaseResponse(SUCCESS.getStatus(), SUCCESS.getCode(), ACCOUNT_CREATION_SUCCESS, buildAccountResponse(savedAccount));
    }
}
