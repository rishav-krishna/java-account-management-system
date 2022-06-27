package com.example.accountmanagementsystem.Dtos;

import com.example.accountmanagementsystem.entities.CustomerPurchasingData;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CustomerPurchasingDto {

  private Integer purchasingId;
  private Integer customerVatTin;
  private String customerPanNbr;
  private String customerTanNbr;
  private String customerUdyogAadharNbr;
  private String customerCreditTermCode;
  private String distributionChannelCode;
  private Integer customerId;

  public static List<CustomerPurchasingDto> getCustomerPurchasingDto(List<CustomerPurchasingData> customerPurchasingData, Integer customerId) {
    return customerPurchasingData.stream()
        .map(cpd -> CustomerPurchasingDto.builder()
             .purchasingId(cpd.getPurchasingId())
             .customerVatTin(cpd.getCustomerVatTin())
             .customerPanNbr(cpd.getCustomerPanNbr())
             .customerTanNbr(cpd.getCustomerPanNbr())
             .customerUdyogAadharNbr(cpd.getCustomerUdyogAadharNbr())
             .customerCreditTermCode(cpd.getCustomerCreditTerm().getCreditTermCode())
             .distributionChannelCode(cpd.getDistributionChannel().getDistChannelCode())
             .customerId(customerId).build())
        .collect(Collectors.toList());
  }
}
