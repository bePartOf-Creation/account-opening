package com.accountopeningapplication.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Configuration
public class AccountOpeningConfigProperties {

    @Value("${nuban-seed}")
    private String seed;
    @Value("${nuban-seed2}")
    private String seed2;
    @Value("${3line-bank-code}")
    private String bankCode;
    @Value("${3line-bank-account-first-two-account}")
    private String firstTwoBankAccount;
    @Value("${pagination-page-number}")
    private int pageNumber;
    @Value("${pagination-page-size}")
    private int pageSize;
}
