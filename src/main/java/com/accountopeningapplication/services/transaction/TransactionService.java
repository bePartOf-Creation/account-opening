package com.accountopeningapplication.services.transaction;

import com.accountopeningapplication.dtos.response.PaginatedTransactionDTO;
import com.accountopeningapplication.entities.Account;
import com.accountopeningapplication.entities.Transaction;

public interface TransactionService {
    Transaction createNewTransaction(Account newCurrentAccount,String customerId,Double initialCreditBalance,String description);
    PaginatedTransactionDTO fetchAllTransactionsByUserAccount(Account account);
}
