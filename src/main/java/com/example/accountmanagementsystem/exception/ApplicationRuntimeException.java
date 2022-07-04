package com.example.accountmanagementsystem.exception;

import lombok.Getter;

@Getter
public class ApplicationRuntimeException extends RuntimeException{
  private ApplicationError applicationError;

  public ApplicationRuntimeException(ApplicationError applicationError) {
    super(applicationError.getMessageWithCode());
    this.applicationError = applicationError;
  }

  public ApplicationRuntimeException(ApplicationError applicationError, Throwable cause) {
    super(applicationError.getMessageWithCode(), cause);
    this.applicationError = applicationError;
  }

  public ApplicationRuntimeException(Throwable cause, ApplicationError applicationError) {
    super(cause);
    this.applicationError = applicationError;
  }
}
