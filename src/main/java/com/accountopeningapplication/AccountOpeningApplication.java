package com.accountopeningapplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Current Account Opening API", version = "1.0", description = "Documentation for Current Account Opening API"))
public class AccountOpeningApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountOpeningApplication.class, args);
    }

}