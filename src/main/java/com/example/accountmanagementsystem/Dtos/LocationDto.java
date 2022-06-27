package com.example.accountmanagementsystem.Dtos;

import com.example.accountmanagementsystem.entities.ExpenseCenterDetail;
import com.example.accountmanagementsystem.entities.LocationDetail;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
public class LocationDto {

  private Integer locationId;
  private String locationName;
  private String locationAddress;
  private Integer locationCode;
  private String accountEnt;
  private String taxEnt;
  private Integer enterpriseId;
  private List<ExpenseCenter> expenseCenters;

  public static LocationDto getLocationDto(LocationDetail locationDetail, final List<ExpenseCenterDetail> expenseCenterDetails) {
    return LocationDto.builder()
        .locationId(locationDetail.getLocationId())
        .locationName(locationDetail.getLocationName())
        .locationAddress(locationDetail.getLocationAddress())
        .locationCode(locationDetail.getLocationCode())
        .accountEnt(locationDetail.getAccountEnt())
        .taxEnt(locationDetail.getTaxEnt())
        .enterpriseId(locationDetail.getEnterpriseDetail().getEnterpriseId())
        .expenseCenters(
            Optional.ofNullable(expenseCenterDetails)
                .orElse(Collections.emptyList())
                .stream()
                .map(e -> new ExpenseCenter(e.getId(), e.getName(), e.getCenterCode()))
                .collect(Collectors.toList())
        ).build();
  }

  private static class ExpenseCenter {
    private final Integer centerId;
    private final String centerName;
    private final Integer centerCode;

    public ExpenseCenter(Integer centerId, String centerName, Integer centerCode) {
      this.centerId = centerId;
      this.centerName = centerName;
      this.centerCode = centerCode;
    }

    public Integer getCenterId() {
      return centerId;
    }

    public String getCenterName() {
      return centerName;
    }

    public Integer getCenterCode() {
      return centerCode;
    }
  }
}
