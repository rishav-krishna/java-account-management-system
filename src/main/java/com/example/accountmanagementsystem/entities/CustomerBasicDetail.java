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
  @JoinColumn(name = "organization_id", nullable = false)
  private OrganizationDetail organizationDetail;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "customer_comm_intermidate", joinColumns =
      {@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")}
      , inverseJoinColumns = {@JoinColumn(name = "customer_comm_id", referencedColumnName = "id")})
  private Set<CustomerCommData> customerCommData = new HashSet<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "customer_bank_intermidate", joinColumns =
      {@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")}
      , inverseJoinColumns = {@JoinColumn(name = "customer_payment_id", referencedColumnName = "customer_payment_id")})
  private Set<CustomerPaymentData> customerPaymentData = new HashSet<>();

  public void addCustomerPaymentData(CustomerPaymentData customerPaymentData) {
    this.customerPaymentData.add(customerPaymentData);
    customerPaymentData.getCustomerBasicDetails().add(this);
  }

  public void removeCustomerPaymentData(Integer customerPaymentId) {
    CustomerPaymentData cpd = this.customerPaymentData.stream().filter(t -> t.getCustomerPaymentId() == customerPaymentId).findFirst().orElse(null);
    if (cpd != null) {
      this.customerPaymentData.remove(cpd);
      cpd.getCustomerBasicDetails().remove(this);
    }
  }

  public void addCustomerCommData(CustomerCommData customerCommData) {
    this.customerCommData.add(customerCommData);
    customerCommData.getCustomerBasicDetails().add(this);
  }

  public void removeCustomerCommData(Integer id) {
    CustomerCommData ccd = this.customerCommData.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    if (ccd != null) {
      this.customerCommData.remove(ccd);
      ccd.getCustomerBasicDetails().remove(this);
    }
  }
}
