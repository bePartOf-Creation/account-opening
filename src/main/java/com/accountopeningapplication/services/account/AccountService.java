package com.accountopeningapplication.services.account;

import com.accountopeningapplication.dtos.response.BaseResponse;
import com.accountopeningapplication.dtos.requests.CurrentAccountDTO;

public interface AccountService {
     BaseResponse openCurrentAccount(CurrentAccountDTO accountDTO);
}
