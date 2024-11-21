package com.accountopeningapplication.services.transaction;

import com.accountopeningapplication.config.AccountOpeningConfigProperties;
import com.accountopeningapplication.dtos.response.PaginatedTransactionDTO;
import com.accountopeningapplication.dtos.response.TransactionDetailsDTO;
import com.accountopeningapplication.entities.Account;
import com.accountopeningapplication.entities.Transaction;
import com.accountopeningapplication.enums.Action;
import com.accountopeningapplication.exception.GeneralException;
import com.accountopeningapplication.exception.ResourceCreationException;
import com.accountopeningapplication.repositories.TransactionHistoryRepository;
import com.accountopeningapplication.utils.HelperUtil;
import com.accountopeningapplication.utils.builders.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.accountopeningapplication.utils.builders.Builder.buildTransaction;
import static com.accountopeningapplication.utils.builders.Builder.paginatedTransactions;
import static com.accountopeningapplication.utils.constants.StringValues.TRANSACTION_CREATION_ERROR;
import static com.accountopeningapplication.utils.constants.StringValues.TRANSACTION_RETRIEVAL_ERROR;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionServiceImpl implements TransactionService {

    private final TransactionHistoryRepository transactionHistoryRepo;
    private final AccountOpeningConfigProperties accountOpeningConfigProperties;


    @Override
    public Transaction createNewTransaction(Account newCurrentAccount, String customerId,
                                            Double initialCreditBalance, String description) {
        Transaction newTransaction = null;
        try {
            newTransaction = buildTransaction(
                    newCurrentAccount, customerId,
                    initialCreditBalance, description
            );
            return this.transactionHistoryRepo.save(newTransaction);
        } catch (ResourceCreationException exception) {
            log.debug(TRANSACTION_CREATION_ERROR, exception.getMessage());
            log.error(TRANSACTION_CREATION_ERROR, exception.getMessage());
        }
        return newTransaction;
    }


    @Override
    public PaginatedTransactionDTO fetchAllTransactionsByUserAccount(Account account) {
        PaginatedTransactionDTO paginatedTransactions = null;
        try {
            Page<Transaction> transactionPageResult = transactionHistoryRepo.findAllByUserAccount(
                    account.getId(), PageRequest.of(
                            this.accountOpeningConfigProperties.getPageNumber(),
                            this.accountOpeningConfigProperties.getPageSize())
            );

            // Map transactions to DTOs
            List<TransactionDetailsDTO> transactionDTOs = transactionPageResult.getContent().stream()
                    .map(Builder::mapToTransactionDetailsDTO)
                    .collect(Collectors.toList());

            // Wrap transactions in PaginatedTransactionDTO
            paginatedTransactions = paginatedTransactions(transactionPageResult, transactionDTOs);

        } catch (GeneralException exception) {
            log.debug(TRANSACTION_RETRIEVAL_ERROR, exception.getMessage());
            log.error(TRANSACTION_RETRIEVAL_ERROR, exception.getMessage());
        }

        return paginatedTransactions;
    }


}
