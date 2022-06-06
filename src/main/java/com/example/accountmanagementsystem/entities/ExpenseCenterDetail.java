package com.example.accountmanagementsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "expense_center_details")
public class ExpenseCenterDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "expense_center_id")
  private Integer id;

  @Column(name = "expense_center_name")
  private String name;

  @Column(name = "expense_center_code")
  private Integer centerCode;

  @ManyToOne
  @JoinColumn(name = "location_id", nullable = false)
  private LocationDetail locationDetail;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCenterCode() {
    return centerCode;
  }

  public void setCenterCode(Integer centerCode) {
    this.centerCode = centerCode;
  }

  public void setLocationDetail(LocationDetail location) {
    this.locationDetail = location;
  }

  public LocationDetail getLocationDetail() {
    return locationDetail;
  }
}
