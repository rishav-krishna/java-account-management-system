package com.example.accountmanagementsystem.Dtos;

import com.example.accountmanagementsystem.entities.EnterpriseDetail;
import com.example.accountmanagementsystem.entities.OrganizationDetail;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
public class OrganizationDto {

  private Integer organizationId;
  private String organizationName;
  private String organizationAddress;
  private List<Enterprise> enterprises;

  public static OrganizationDto getOrganizationDto(final OrganizationDetail organizationDetail, final List<EnterpriseDetail> enterpriseDetails) {

    return OrganizationDto.builder()
        .organizationId(organizationDetail.getOrgId())
        .organizationName(organizationDetail.getOrgName())
        .organizationAddress(organizationDetail.getAddress())
        .enterprises(
            Optional.ofNullable(enterpriseDetails)
                .orElse(Collections.emptyList())
                .stream()
                .map(e -> new Enterprise(e.getEnterpriseId(), e.getEnterpriseName(), e.getEnterpriseAddress(), e.getEnterpriseCode())).collect(Collectors.toList())
        )
        .build();
  }

  @Getter
  private static class Enterprise {
    private final Integer enterpriseId;
    private final String enterpriseName;
    private final String enterpriseAddress;
    private final Integer enterpriseCode;

    public Enterprise(Integer enterpriseId, String enterpriseName, String enterpriseAddress,
                      Integer enterpriseCode) {
      this.enterpriseId = enterpriseId;
      this.enterpriseName = enterpriseName;
      this.enterpriseAddress = enterpriseAddress;
      this.enterpriseCode = enterpriseCode;
    }
  }
}
