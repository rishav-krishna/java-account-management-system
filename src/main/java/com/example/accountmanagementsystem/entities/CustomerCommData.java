package com.example.accountmanagementsystem.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer_comm_data")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter@Setter
public class CustomerCommData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "communication_data")
  private String communicationData;

  @Column(name = "communication_type")
  private String communicationType;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "customerCommData")
  private Set<CustomerBasicDetail> customerBasicDetails = new java.util.LinkedHashSet<>();;
}
