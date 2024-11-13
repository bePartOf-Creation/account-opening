package com.accountopeningapplication.utils.builders;

import com.accountopeningapplication.entities.Account;
import com.accountopeningapplication.entities.Customer;
import com.accountopeningapplication.entities.Transaction;
import com.accountopeningapplication.enums.AccountType;
import com.accountopeningapplication.enums.Tierlevel;
import com.accountopeningapplication.exception.dtos.response.*;
import com.accountopeningapplication.utils.HelperUtil;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Builder {


    public static Customer buildExistingCustomer(String firstname, String lastname,
                                                 String mobileNo, String email) {

        var bvn = HelperUtil.generateRandomNumber(11);
        return Customer.builder()
                .firstname(firstname).lastname(lastname)
                .mobileNo(mobileNo).email(email)
                .isBvnVerified(true).isNINVerified(true)
                .accounts(new ArrayList<>())
                .bvn(bvn)
                .build();
    }

    public static Account buildCustomerAccount(Customer customer,
                                               Double balance,
                                               String accountNumber, Tierlevel tierLevel,
                                               AccountType accountType) {
        return Account.builder()
                .accountNumber(accountNumber)
                .accountTier(tierLevel)
                .accountType(accountType)
                .customer(customer)
                .transactions(new ArrayList<>())
                .balance(BigDecimal.valueOf(balance))
                .isActive(true)
                .isClosed(false)
                .build();
    }

    public static AccountOpeningResponse buildAccountResponse(Account savedAccount) {
        return AccountOpeningResponse.builder()
                .accountNumber(savedAccount.getAccountNumber())
                .accountTier(savedAccount.getAccountTier())
                .accountType(savedAccount.getAccountType())
                .customerId(savedAccount.getCustomer().getId())
                .balance(savedAccount.getBalance())
                .isActive(savedAccount.isActive())
                .isClosed(savedAccount.isClosed())
                .build();
    }

    public static TransactionDetailsDTO mapToTransactionDetailsDTO(Transaction transaction) {
        return TransactionDetailsDTO.builder()
                .accountId(transaction.getAccount().getId())
                .action(transaction.getAction())
                .amount(transaction.getAmount())
                .balance(transaction.getBalance())
                .customerId(transaction.getCustomerId())
                .description(transaction.getDescription())
                .reference(transaction.getReference())
                .status(transaction.getStatus())
                .build();
    }

    public static PaginatedTransactionDTO paginatedTransactions(Page<Transaction> transactionPageResult,
                                                                List<TransactionDetailsDTO> transactionDTOs) {
        PaginatedTransactionDTO paginatedTransactionDTO = new PaginatedTransactionDTO();
        paginatedTransactionDTO.setPaged(transactionDTOs);
        paginatedTransactionDTO.setTotalPages(transactionPageResult.getTotalPages());
        paginatedTransactionDTO.setTotalElements(transactionPageResult.getTotalElements());
        return paginatedTransactionDTO;
    }


    public static AccountDetailsDTO accountDetailsDTO(Account account,
                                                      PaginatedTransactionDTO paginatedTransactionDTO) {
        AccountDetailsDTO accountDTO = new AccountDetailsDTO();
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setAccountTier(account.getAccountTier());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setClosed(account.isClosed());
        accountDTO.setCustomerId(account.getCustomer().getId());
        accountDTO.setActive(account.isActive());
        accountDTO.setTransactions(paginatedTransactionDTO);
        return accountDTO;
    }

    public static CustomerDetailsDTO buildCustomerDTO(Customer customer) {
        CustomerDetailsDTO customerDTO = new CustomerDetailsDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstname(customer.getFirstname());
        customerDTO.setLastname(customer.getLastname());
        customerDTO.setBvn(customer.getBvn());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setBvnVerified(customer.isBvnVerified());
        customerDTO.setMobileNo(customer.getMobileNo());
        customerDTO.setNINVerified(customer.isNINVerified());
        return customerDTO;
    }
}
