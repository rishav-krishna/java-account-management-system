package com.example.accountmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class EnterpriseRequest {
  private String enterpriseName;
  private String enterpriseAddress;
  private Integer organizationId;
}
