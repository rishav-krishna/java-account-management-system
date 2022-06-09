package com.example.accountmanagementsystem.Dtos;

import com.example.accountmanagementsystem.entities.EnterpriseDetail;
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
@Builder
@Getter
@Setter
public class EnterpriseDto {

  private Integer enterpriseId;
  private String enterpriseName;
  private String enterpriseAddress;
  private Integer enterpriseCode;
  private Integer organizationId;
  private List<Location> locations;
  public static EnterpriseDto getEnterpriseDto(EnterpriseDetail enterpriseDetail) {
    return EnterpriseDto.builder()
        .enterpriseId(enterpriseDetail.getEnterpriseId())
        .enterpriseName(enterpriseDetail.getEnterpriseName())
        .enterpriseAddress(enterpriseDetail.getEnterpriseAddress())
        .enterpriseCode(enterpriseDetail.getEnterpriseCode())
        .organizationId(enterpriseDetail.getOrganizationDetail().getOrgId())
        .locations(

            Optional.ofNullable(enterpriseDetail.getLocationDetailSet())
                .orElse(Collections.emptySet())
                .stream()
                .map(l -> new Location(l.getLocationId(), l.getLocationName(), l.getLocationAddress(), l.getLocationCode()))
                .collect(Collectors.toList())
        ).build();
  }

  private static class Location {
    private final Integer locationId;
    private final String locationName;
    private final String locationAddress;
    private final Integer locationCode;

    public Location(Integer locationId, String locationName, String locationAddress,
                    Integer locationCode) {
      this.locationId = locationId;
      this.locationName = locationName;
      this.locationAddress = locationAddress;
      this.locationCode = locationCode;
    }

    public Integer getLocationId() {
      return locationId;
    }

    public String getLocationName() {
      return locationName;
    }

    public String getLocationAddress() {
      return locationAddress;
    }

    public Integer getLocationCode() {
      return locationCode;
    }
  }
}
