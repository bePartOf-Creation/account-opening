package com.accountopeningapplication.exception;

import com.accountopeningapplication.dtos.response.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.accountopeningapplication.enums.ResponseStatus.*;


@ControllerAdvice(annotations = {RestController.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseBody
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundException(ResourceNotFoundException e, HttpServletResponse servletResponse) throws IOException {
        BaseResponse response = new BaseResponse();
        response.setStatus(FAILED.getStatus());
        response.setCode(e.getCode());
        response.setMessage(e.getMessage());

        HttpServletResponse httpServletResponse = this.getWritableServeletResponse(servletResponse, e.getCode());
        String responseJson = MAPPER.writeValueAsString(response);
        httpServletResponse.getWriter().write(responseJson);
    }

    @ExceptionHandler(value = MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        BaseResponse response = new BaseResponse();
        response.setStatus(FAILED.getStatus());
        response.setCode(INVALID_REQUEST.getCode());
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = JsonProcessingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleJsonProcessingException(JsonProcessingException e) {
        BaseResponse response = new BaseResponse();
        response.setStatus(FAILED.getStatus());
        response.setCode(GENERAL_ERROR.getCode());
        response.setMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler(value = GeneralException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleGeneralException(GeneralException e, HttpServletResponse servletResponse) throws IOException {
        BaseResponse response = new BaseResponse(
                FAILED.getStatus(),
                GENERAL_ERROR.getCode(),
                e.getMessage(),
                null);

        HttpServletResponse httpServletResponse = this.getWritableServeletResponse(servletResponse, e.getCode());
        String responseJson = MAPPER.writeValueAsString(response);
        httpServletResponse.getWriter().write(responseJson);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new BaseResponse(
                INVALID_FIELDS_PROVIDED.getStatus(),
                INVALID_FIELDS_PROVIDED.getCode(),
                FAILED.getStatus(),
                errors),
                HttpStatus.BAD_REQUEST);
    }

    private HttpServletResponse getWritableServeletResponse(@NonNull HttpServletResponse servletResponse, String statusCode) {
        servletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        servletResponse.setStatus(Integer.parseInt(statusCode));
        return servletResponse;
    }

}
