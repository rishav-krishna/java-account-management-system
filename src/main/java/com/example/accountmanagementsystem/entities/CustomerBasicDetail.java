package com.example.accountmanagementsystem.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer_basic_details")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter@Setter
public class CustomerBasicDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id")
  private Integer customerId;

  @Column(name = "customer_code")
  private String customerCode;

  @Column(name = "customer_name")
  private String customerName;

  @Column(name = "customer_address")
  private String customerAddress;

  @Column(name = "customer_pincode")
  private Integer customerPinCode;

  @ManyToOne
  @JoinColumn(name = "org_id_fk", nullable = false)
  private OrganizationDetail organizationDetail;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "customer_comm_intermidate", joinColumns =
      {@JoinColumn(referencedColumnName = "customer_id")}
      , inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
  private Set<CustomerCommData> customerCommData = new java.util.LinkedHashSet<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "customer_bank_intermidate", joinColumns =
      {@JoinColumn(referencedColumnName = "customer_id")}
      , inverseJoinColumns = {@JoinColumn(referencedColumnName = "customer_payment_id")})
  private Set<CustomerPaymentData> customerPaymentData = new java.util.LinkedHashSet<>();;
}
