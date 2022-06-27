package com.example.accountmanagementsystem.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRequest {
  private String customerName;
  private String customerAddress;
  private Integer pinCode;
  private Integer organizationId;
  private List<CustomerPaymentRequest> customerPaymentRequests;
  private List<CustomerCommunicationRequest> customerCommunicationRequests;
  private List<CustomerPurchasingRequest> customerPurchasingRequests;
}
