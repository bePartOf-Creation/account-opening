package com.accountopeningapplication.services.customer;

import com.accountopeningapplication.dtos.response.BaseResponse;
import com.accountopeningapplication.dtos.response.CustomerDetailsDTO;
import com.accountopeningapplication.exception.GeneralException;
import com.accountopeningapplication.exception.ResourceNotFoundException;
import com.accountopeningapplication.repositories.CustomerRepository;
import com.accountopeningapplication.services.transaction.TransactionService;
import com.accountopeningapplication.utils.builders.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

import static com.accountopeningapplication.enums.ResponseStatus.*;
import static com.accountopeningapplication.utils.builders.Builder.accountDetailsDTO;
import static com.accountopeningapplication.utils.constants.StringValues.*;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepo;
    private final TransactionService transactionService;

    @Override
    public BaseResponse getCustomerWithPaginatedTransactions(String customerId) {

        if (Objects.isNull(customerId)) {
            return new BaseResponse(INVALID_REQUEST.getCode(), INVALID_REQUEST.getStatus(), INVALID_REQUEST.name(), REQUIRED);
        }

        CustomerDetailsDTO customerDTO = null;
        try {
            var customer = customerRepo.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException(RECORD_NOT_FOUND.getStatus(), RECORD_NOT_FOUND.getCode()));

            customerDTO = Builder.buildCustomerDTO(customer);

            var accountDTOs = customer.getAccounts()
                    .stream()
                    .map(account -> {
                        var paginatedTransactionDTO = transactionService.fetchAllTransactionsByUserAccount(account);
                        return accountDetailsDTO(account, paginatedTransactionDTO);
                    }).collect(Collectors.toList());

            customerDTO.setAccounts(accountDTOs);
        } catch (GeneralException exception) {
            log.debug(CUSTOMER_DETAILS_RETRIEVAL_ERROR, exception.getMessage());
            log.error(CUSTOMER_DETAILS_RETRIEVAL_ERROR, exception.getMessage());
        }

        return new BaseResponse(SUCCESS.getStatus(), SUCCESS.getCode(), CUSTOMER_DETAILS_RETRIEVAL_SUCCESS, customerDTO);

    }

}
