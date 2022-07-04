package com.example.accountmanagementsystem.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerPaymentRequest implements Serializable {

  private String customerAccountNumber;
  private String IfscCode;
  private String accountType;
  private String accountHolderName;
}
