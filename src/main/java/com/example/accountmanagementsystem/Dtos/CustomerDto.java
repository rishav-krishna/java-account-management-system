package com.example.accountmanagementsystem.Dtos;

import com.example.accountmanagementsystem.entities.CustomerBasicDetail;
import com.example.accountmanagementsystem.entities.CustomerCommData;
import com.example.accountmanagementsystem.entities.CustomerPaymentData;
import com.example.accountmanagementsystem.entities.CustomerPurchasingData;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto {

  private Integer customerId;
  private String customerName;
  private String customerAddress;
  private String customerCode;
  private Integer pinCode;
  private List<CustomerPurchasingDto> customerPurchasingDtos;
  private List<CustomerPaymentDto> customerPaymentDtos;
  private List<CustomerCommDto> customerCommDtos;

  public static CustomerDto getCustomerDto(final CustomerBasicDetail customerBasicDetail,
                                           final List<CustomerPurchasingData> customerPurchasingData,
                                           final List<CustomerPaymentData> customerPaymentData,
                                           final List<CustomerCommData> customerCommData) {
    return CustomerDto.builder()
        .customerId(customerBasicDetail.getCustomerId())
        .customerCode(customerBasicDetail.getCustomerCode())
        .customerName(customerBasicDetail.getCustomerName())
        .customerAddress(customerBasicDetail.getCustomerAddress())
        .pinCode(customerBasicDetail.getCustomerPinCode())
        .customerPurchasingDtos(CustomerPurchasingDto.getCustomerPurchasingDto(customerPurchasingData, customerBasicDetail.getCustomerId()))
        .customerPaymentDtos(CustomerPaymentDto.getCustomerPaymentDto(customerPaymentData, customerBasicDetail.getCustomerId()))
        .customerCommDtos(CustomerCommDto.getCustomerCommDtos(customerCommData, customerBasicDetail.getCustomerId()))
        .build();
  }
}
