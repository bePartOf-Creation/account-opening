package com.accountopeningapplication.controllers;

import com.accountopeningapplication.exception.dtos.requests.CurrentAccountDTO;
import com.accountopeningapplication.exception.dtos.response.BaseResponse;
import com.accountopeningapplication.services.account.AccountService;
import com.accountopeningapplication.services.customer.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.accountopeningapplication.utils.constants.StringValues.*;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RequestMapping("/api/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class AccountOpeningController {

    private final AccountService accountService;
    private final CustomerService customerService;


    @Operation(summary = CURRENT_ACCT_OPENING_OPERATION, description = CURRENT_ACCT_OPENING_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = CURRENT_ACCT_OPENED),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST),
            @ApiResponse(responseCode = "500", description = INTERNAL_SERVER_ERROR)
    })
    @PostMapping("/customer/current-account")
    public ResponseEntity<BaseResponse> openCurrentAccount(@Valid @RequestBody CurrentAccountDTO accountDTO) {
        return ResponseEntity.status(CREATED).body(this.accountService.openCurrentAccount(accountDTO));
    }

    @Operation(summary = GET_CUSTOMER_DETAILS_OPERATION, description = CURRENT_ACCT_OPENING_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = CUSTOMER_DETAILS_FETCHED),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST),
            @ApiResponse(responseCode = "500", description = INTERNAL_SERVER_ERROR)
    })
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> fetchCustomerDetails(@PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(this.customerService.getCustomerWithPaginatedTransactions(customerId));
    }

}
