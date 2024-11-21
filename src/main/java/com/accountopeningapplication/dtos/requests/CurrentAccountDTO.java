package com.accountopeningapplication.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@Schema(description = "Data transfer object for Current Account Opening")
public class CurrentAccountDTO {
    @Schema(description = "Unique ID of a customer", example = "01JCH36TZ5F70FV6EDX840PB23")
    @NotNull(message = "Customer Id must not be empty.")
    private String customerId;
    @Schema(description = "Initial Balance of Existing Customer's Account", example = "1000.0")
    private Double initialBalance;
}
