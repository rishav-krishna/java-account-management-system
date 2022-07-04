package com.example.accountmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
  private int status;
  private String error;
  @JsonProperty("errorCode")
  private String applicationErrorCode;
  private String message;
  private Map<String, Object> additionalDetails;
}
