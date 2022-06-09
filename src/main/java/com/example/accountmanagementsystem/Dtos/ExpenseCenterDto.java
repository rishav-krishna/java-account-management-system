package com.example.accountmanagementsystem.Dtos;

import com.example.accountmanagementsystem.entities.ExpenseCenterDetail;
import java.util.ArrayList;
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
public class ExpenseCenterDto {

  private Integer centerId;
  private String centerName;
  private Integer centerCode;
  private Integer locationId;

  public static List<ExpenseCenterDto> getExpenseCenterDtos(List<ExpenseCenterDetail> expenseCenterDetails) {

    return expenseCenterDetails.stream().map(expenseCenterDetail -> ExpenseCenterDto.builder()
        .centerId(expenseCenterDetail.getId())
        .centerName(expenseCenterDetail.getName())
        .centerCode(expenseCenterDetail.getCenterCode())
        .locationId(expenseCenterDetail.getLocationDetail().getLocationId())
        .build()).collect(Collectors.toList());
  }
}
