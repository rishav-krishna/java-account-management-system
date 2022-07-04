package com.example.accountmanagementsystem.exception;

public class DuplicateDataException extends ApplicationRuntimeException{
  public DuplicateDataException(ApplicationError applicationError) {
    super(applicationError);
  }

  public DuplicateDataException(ApplicationError applicationError, Throwable cause) {
    super(applicationError, cause);
  }

  public DuplicateDataException(Throwable cause, ApplicationError applicationError) {
    super(cause, applicationError);
  }
}
