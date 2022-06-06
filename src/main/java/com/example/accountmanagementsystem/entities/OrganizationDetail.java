package com.example.accountmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organization_details")
public class OrganizationDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "organization_id", unique = true, nullable = false)
  private Integer orgId;

  @Column(name = "organization_name", unique = true)
  private String orgName;

  @Column(name = "organization_address")
  private String address;

//  @Column(name = "is_active", columnDefinition = "int default 1", nullable = false)
//  private Integer isActive;

  @Transient
  private Integer isActive;

  @OneToMany(mappedBy = "organizationDetail")
  private Set<EnterpriseDetail> enterpriseDetails;

  public Integer getOrgId() {
    return orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public String getAddress() {
    return address;
  }

  public Integer getIsActive() {
    return isActive;
  }

  public void setOrgId(Integer orgId) {
    this.orgId = orgId;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setIsActive(Integer isActive) {
    this.isActive = isActive;
  }

  public void setEnterpriseDetails(
      Set<EnterpriseDetail> enterpriseDetails) {
    this.enterpriseDetails = enterpriseDetails;
    for(EnterpriseDetail enterpriseDetail: enterpriseDetails) {
      enterpriseDetail.setOrganizationDetail(this);
    }
  }
}
