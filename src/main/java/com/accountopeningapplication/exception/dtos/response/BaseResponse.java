package com.accountopeningapplication.exception.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private String status;
    private String code;
    private String message;
    private Object data;
}
