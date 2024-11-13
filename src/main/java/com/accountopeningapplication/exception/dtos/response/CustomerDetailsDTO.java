package com.accountopeningapplication.exception.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomerDetailsDTO {
    private String id;
    private String firstname;
    private String lastname;
    private String mobileNo;
    private String bvn;
    private String email;
    private boolean isBvnVerified;
    private boolean isNINVerified;
    private List<AccountDetailsDTO> accounts = new ArrayList<>();
}
