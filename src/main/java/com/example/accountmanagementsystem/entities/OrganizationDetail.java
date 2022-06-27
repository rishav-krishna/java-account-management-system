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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organization_details")
@Getter@Setter
public class OrganizationDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "organization_id", unique = true, nullable = false)
  private Integer orgId;

  @Column(name = "organization_name", unique = true)
  private String orgName;

  @Column(name = "organization_address")
  private String address;

  @Column(name = "is_active", columnDefinition = "int default 1", nullable = false)
  private Integer isActive;
}
