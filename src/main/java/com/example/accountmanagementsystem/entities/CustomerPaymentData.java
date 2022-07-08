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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CustomerPaymentData that = (CustomerPaymentData) o;

    if (customerPaymentId != null ? !customerPaymentId.equals(that.customerPaymentId) :
        that.customerPaymentId != null) {
      return false;
    }
    if (customerAccountNbr != null ? !customerAccountNbr.equals(that.customerAccountNbr) :
        that.customerAccountNbr != null) {
      return false;
    }
    if (bankIfscCode != null ? !bankIfscCode.equals(that.bankIfscCode) :
        that.bankIfscCode != null) {
      return false;
    }
    if (accountType != null ? !accountType.equals(that.accountType) : that.accountType != null) {
      return false;
    }
    if (accountHolderName != null ? !accountHolderName.equals(that.accountHolderName) :
        that.accountHolderName != null) {
      return false;
    }
    return customerBasicDetails != null ? customerBasicDetails.equals(that.customerBasicDetails) :
        that.customerBasicDetails == null;
  }

  @Override
  public int hashCode() {
    int result = customerPaymentId != null ? customerPaymentId.hashCode() : 0;
    result = 31 * result + (customerAccountNbr != null ? customerAccountNbr.hashCode() : 0);
    result = 31 * result + (bankIfscCode != null ? bankIfscCode.hashCode() : 0);
    result = 31 * result + (accountType != null ? accountType.hashCode() : 0);
    result = 31 * result + (accountHolderName != null ? accountHolderName.hashCode() : 0);
    result = 31 * result + (customerBasicDetails != null ? customerBasicDetails.hashCode() : 0);
    return result;
  }
}
