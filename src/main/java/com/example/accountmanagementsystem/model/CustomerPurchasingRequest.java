package com.example.accountmanagementsystem.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerPurchasingRequest {

  private Integer purchasingId;
  private Integer customerVatTin;
  private String customerGstNbr;
  private String customerPanNbr;
  private String customerTanNbr;
  private String customerUdyogAadharNbr;
  private String customerCreditTermCode;
  private String distributionChannelCode;
}
