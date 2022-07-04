package com.example.accountmanagementsystem.exception;

import com.example.accountmanagementsystem.constants.Errors;
import com.example.accountmanagementsystem.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErpExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {InvalidRequestException.class})
  public ResponseEntity<ErrorResponse> handleInvalidRequestException(
      final InvalidRequestException ex) {
    log.error("Error occurred at", ex);
    return toErrorResponse(ex.getApplicationError(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {Throwable.class})
  public ResponseEntity<ErrorResponse> anyOtherException(
      final Throwable ex) {
    log.error("Error occurred at ", ex);
    return toErrorResponse(Errors.General.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<ErrorResponse> toErrorResponse(ApplicationError error,
                                                        HttpStatus httpStatus) {
    return new ResponseEntity<>(ErrorResponse.builder().status(httpStatus.value())
        .additionalDetails(error.getAdditionalDetails())
        .error(httpStatus.getReasonPhrase())
        .message(error.getMessage())
        .applicationErrorCode(error.getCode())
        .build(), httpStatus);
  }
}
