package com.accountopeningapplication.services.customer;

import com.accountopeningapplication.dtos.response.BaseResponse;

public interface CustomerService {
    BaseResponse getCustomerWithPaginatedTransactions(String customerId);
}
