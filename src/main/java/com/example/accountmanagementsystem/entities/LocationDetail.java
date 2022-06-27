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
@Getter@Setter
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
}
