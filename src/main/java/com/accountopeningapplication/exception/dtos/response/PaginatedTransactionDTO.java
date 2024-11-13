package com.accountopeningapplication.exception.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginatedTransactionDTO {
    private List<TransactionDetailsDTO> paged;
    private int totalPages;
    private long totalElements;

}
