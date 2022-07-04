package com.example.accountmanagementsystem.constants;

import com.example.accountmanagementsystem.exception.ApplicationError;

public class Errors {

  public static final class General {
    public static final ApplicationError BAD_REQUEST =
        new ApplicationError("1000", "Invalid request payload");
    public static final ApplicationError INTERNAL_SERVER_ERROR =
        new ApplicationError("1001", "A server-side error occurred");
    public static final ApplicationError BAD_TOKEN_PAYLOAD_DATA =
        new ApplicationError("1002", "Mandatory fields are missing to generate Token payload");
  }

  public static final class Organization {
    public static final ApplicationError INVALID_ORG_ID =
        new ApplicationError("1100", "Invalid Organization Id");
    public static final ApplicationError INVALID_ENTERPRISE_ID =
        new ApplicationError("1101", "Invalid Enterprise Id");
    public static final ApplicationError INVALID_LOCATION_ID =
        new ApplicationError("1102", "Invalid Location Id");
  }

  public static final class Customer {
    public static final ApplicationError INVALID_CUSTOMER_ID =
        new ApplicationError("1200", "Invalid Customer Id");
  }
}
