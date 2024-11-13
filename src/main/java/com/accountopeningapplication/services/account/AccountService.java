package com.accountopeningapplication.services.account;

import com.accountopeningapplication.exception.dtos.response.BaseResponse;
import com.accountopeningapplication.exception.dtos.requests.CurrentAccountDTO;

public interface AccountService {
     BaseResponse openCurrentAccount(CurrentAccountDTO accountDTO);
}
