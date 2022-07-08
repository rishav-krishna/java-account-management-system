package com.example.accountmanagementsystem.Dtos;

import com.example.accountmanagementsystem.entities.CustomerCommData;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class CustomerCommDto implements Serializable {

  private Integer id;
  private String communicationData;
  private String communicationType;
  private Integer customerId;

  public static List<CustomerCommDto> getCustomerCommDtos(final List<CustomerCommData> customerCommData, Integer customerId) {
    return customerCommData.stream().map(ccd -> CustomerCommDto.builder()
        .id(ccd.getId())
        .communicationData(ccd.getCommunicationData())
        .communicationType(ccd.getCommunicationType())
        .customerId(customerId)
        .build()).collect(Collectors.toList());
  }
}
