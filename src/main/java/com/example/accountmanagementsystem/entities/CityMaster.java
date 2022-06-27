package com.example.accountmanagementsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "city_master")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class CityMaster {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "city_id")
  private Integer cityId;

  @Column(name = "city_name")
  private String cityName;

  @Column(name = "city_pincode", unique = true)
  private Integer cityPinCode;

  @Column(name = "district_name")
  private String districtName;

  @Column(name = "state_name")
  private String stateName;

  @Column(name = "contry_Name")
  private String countryName;
}
