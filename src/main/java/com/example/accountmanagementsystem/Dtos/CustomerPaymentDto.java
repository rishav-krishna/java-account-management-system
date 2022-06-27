package com.example.accountmanagementsystem.Dtos;

import com.example.accountmanagementsystem.entities.CustomerPaymentData;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPaymentDto {

  private Integer customerPaymentId;
  private String customerAccountNbr;
  private String bankIfscCode;
  private String accountType;
  private String accountHolderName;
  private Integer customerId;


  public static List<CustomerPaymentDto> getCustomerPaymentDto(final List<CustomerPaymentData> customerPaymentData, Integer customerId){
    return customerPaymentData.stream().map(cpd -> CustomerPaymentDto.builder()
        .customerPaymentId(cpd.getCustomerPaymentId())
        .customerAccountNbr(cpd.getCustomerAccountNbr())
        .bankIfscCode(cpd.getBankIfscCode().getBranchIfscCode())
        .accountType(cpd.getAccountType())
        .accountHolderName(cpd.getAccountHolderName())
        .customerId(customerId)
        .build()).collect(Collectors.toList());
  }
}
