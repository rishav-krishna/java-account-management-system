package com.example.accountmanagementsystem.exception;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;

@Getter
public class ApplicationError {

  private final String code;
  private final String message;
  private final Map<String, Object> additionalDetails;

  public ApplicationError(String code, String message) {
    Objects.requireNonNull(code, "code should not null");
    Objects.requireNonNull(message, "message should not null");
    this.code = code;
    this.message = message;
    additionalDetails = Collections.emptyMap();
  }

  public String getMessage() {
    return message;
  }

  public String getMessageWithCode() {
    return code + ": " + message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ApplicationError that = (ApplicationError) o;
    return code.equals(that.code) && message.equals(that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    return "ApplicationError{" +
        "code='" + code + '\'' +
        ", message='" + message + '\'' +
        '}';
  }
}
