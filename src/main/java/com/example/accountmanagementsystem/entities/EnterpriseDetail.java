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
@Table(name = "enterprise_details")
@Getter@Setter
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
}
