package com.example.accountmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerPaymentRequest {

  private String customerAccountNumber;
  private String IfscCode;
  private String accountType;
  private String accountHolderName;
}
