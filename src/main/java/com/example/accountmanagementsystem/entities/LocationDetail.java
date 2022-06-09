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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "location_details")
public class LocationDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "location_id", unique = true)
  private Integer locationId;

  @Column(name = "location_name", unique = true)
  private String locationName;

  @Column(name = "location_address")
  private String locationAddress;

  @Transient
  private Integer isActive;

  @Column(name = "location_code")
  private Integer locationCode;

  @Column(name = "account_ent")
  private String accountEnt;

  @Column(name = "tax_ent")
  private String taxEnt;

  @ManyToOne
  @JoinColumn(name = "enterprise_id", nullable = false)
  private EnterpriseDetail enterpriseDetail;

  @OneToMany(mappedBy = "locationDetail")
  private Set<ExpenseCenterDetail> expenseCenterDetailSet;

  public Integer getLocationId() {
    return locationId;
  }

  public void setLocationId(Integer locationId) {
    this.locationId = locationId;
  }

  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public String getLocationAddress() {
    return locationAddress;
  }

  public void setLocationAddress(String locationAddress) {
    this.locationAddress = locationAddress;
  }

  public EnterpriseDetail getEnterpriseDetail() {
    return enterpriseDetail;
  }

  public void setEnterpriseDetail(
      EnterpriseDetail enterpriseDetail) {
    this.enterpriseDetail = enterpriseDetail;
  }

  public Integer getIsActive() {
    return isActive;
  }

  public void setIsActive(Integer isActive) {
    this.isActive = isActive;
  }

  public Integer getLocationCode() {
    return locationCode;
  }

  public void setLocationCode(Integer locationCode) {
    this.locationCode = locationCode;
  }

  public String getAccountEnt() {
    return accountEnt;
  }

  public void setAccountEnt(String accountEnt) {
    this.accountEnt = accountEnt;
  }

  public String getTaxEnt() {
    return taxEnt;
  }

  public void setTaxEnt(String taxEnt) {
    this.taxEnt = taxEnt;
  }

  public void setExpenseCenterDetailSet(
      Set<ExpenseCenterDetail> expenseCenterDetailSet) {
    this.expenseCenterDetailSet = expenseCenterDetailSet;
  }

  public Set<ExpenseCenterDetail> getExpenseCenterDetailSet() {
    return expenseCenterDetailSet;
  }
}
