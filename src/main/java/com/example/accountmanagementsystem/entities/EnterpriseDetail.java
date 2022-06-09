package com.example.accountmanagementsystem.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "enterprise_details")
public class EnterpriseDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "enterprise_id", unique = true)
  private Integer enterpriseId;

  @Column(name = "enterprise_name", unique = true)
  private String enterpriseName;

  @Column(name = "enterprise_address")
  private String enterpriseAddress;

  @Transient
  private Integer isActive;

  @Column(name = "enterprise_code")
  private Integer enterpriseCode;

  @ManyToOne
  @JoinColumn(name = "organization_id", nullable = false)
  private OrganizationDetail organizationDetail;

  @OneToMany(mappedBy = "enterpriseDetail")
  private Set<LocationDetail> locationDetailSet;


  public Integer getEnterpriseId() {
    return enterpriseId;
  }

  public void setEnterpriseId(Integer enterpriseId) {
    this.enterpriseId = enterpriseId;
  }

  public String getEnterpriseName() {
    return enterpriseName;
  }

  public void setEnterpriseName(String enterpriseName) {
    this.enterpriseName = enterpriseName;
  }

  public String getEnterpriseAddress() {
    return enterpriseAddress;
  }

  public void setEnterpriseAddress(String enterpriseAddress) {
    this.enterpriseAddress = enterpriseAddress;
  }

  public Integer getIsActive() {
    return isActive;
  }

  public void setIsActive(Integer isActive) {
    this.isActive = isActive;
  }

  public Integer getEnterpriseCode() {
    return enterpriseCode;
  }

  public void setEnterpriseCode(Integer enterpriseCode) {
    this.enterpriseCode = enterpriseCode;
  }

  public OrganizationDetail getOrganizationDetail() {
    return organizationDetail;
  }

  public void setOrganizationDetail(
      OrganizationDetail organizationDetail) {
    this.organizationDetail = organizationDetail;
  }

  public void setLocationDetailSet(
      Set<LocationDetail> locationDetailSet) {
    this.locationDetailSet = locationDetailSet;
  }

  public Set<LocationDetail> getLocationDetailSet() {
    return locationDetailSet;
  }
}
