package com.accountopeningapplication.services.customer;

import com.accountopeningapplication.exception.dtos.response.BaseResponse;

public interface CustomerService {
    BaseResponse getCustomerWithPaginatedTransactions(String customerId);
}
