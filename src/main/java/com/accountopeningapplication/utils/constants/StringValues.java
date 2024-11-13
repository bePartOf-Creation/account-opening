package com.accountopeningapplication.utils.constants;

public interface StringValues {

    String RESOURCE_NOT_FOUND = "Resource not found";
    String OPENING_BALANCE = "Opening Balance Amount of ";
    String ACCOUNT_CREATION_SUCCESS = "Account creation was successful";
    String CUSTOMER_DETAILS_RETRIEVAL_SUCCESS = "Customer Details was retrieved successful";
    String DATABASE_SEEDING_ERROR = "Database Seeding Error -> {}";
    String ACCOUNT_OPENING_ERROR = "Account Opening Error -> {} ";
    String TRANSACTION_CREATION_ERROR = "Transaction Creation Error -> {} ";
    String TRANSACTION_RETRIEVAL_ERROR = "Transaction Retrieval Error -> {} ";
    String CUSTOMER_DETAILS_RETRIEVAL_ERROR = "Customer Details Retrieval Error -> {} ";
    String CURRENT_ACCT_OPENING_DESCRIPTION = "Create A new Current Account With existing customer details.";
    String CURRENT_ACCT_OPENING_OPERATION = "Open A new Current Account";
    String CURRENT_ACCT_OPENED = "Current Account Opened";
    String CUSTOMER_DETAILS_FETCHED = "Customer Detail Was Retrieved.";
    String BAD_REQUEST = "Bad Request";
    String INTERNAL_SERVER_ERROR = "Internal Server Error";
    String MINIMUM_BALANCE = "Minimum Balance for current account should be up to N1,000";
    String REQUIRED = "Customer Id Is Required";
    String GET_CUSTOMER_DETAILS_OPERATION = "Fetch Customer Details with accounts and Transactions";
}
