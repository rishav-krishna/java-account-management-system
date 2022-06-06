package com.example.accountmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter@Setter
@ToString
public class EnterpriseLocationRequest {

  private String enterpriseName;
  private String enterpriseAddress;
  private String locationName;
  private String locationAddress;
  private Integer organizationId;
}
