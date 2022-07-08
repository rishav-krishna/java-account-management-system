package com.example.accountmanagementsystem.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer_payment_data")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter@Setter
public class CustomerPaymentData implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_payment_id")
  private Integer customerPaymentId;

  @Column(name = "customer_account_number")
  private String customerAccountNbr;

  @ManyToOne
  @JoinColumn(name = "branch_ifsc_code", nullable = false)
  private BankIfscMaster bankIfscCode;

  @Column(name = "account_type")
  private String accountType;

  @Column(name = "account_holder_name")
  private String accountHolderName;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "customerPaymentData")
  private Set<CustomerBasicDetail> customerBasicDetails = new HashSet<>();
}
