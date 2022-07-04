package com.example.accountmanagementsystem.exception;

import lombok.Getter;

public class InvalidRequestException extends ApplicationRuntimeException{
  public InvalidRequestException(ApplicationError applicationError) {
    super(applicationError);
  }

  public InvalidRequestException(ApplicationError applicationError, Throwable cause) {
    super(applicationError, cause);
  }

  public InvalidRequestException(Throwable cause, ApplicationError applicationError) {
    super(cause, applicationError);
  }
}
