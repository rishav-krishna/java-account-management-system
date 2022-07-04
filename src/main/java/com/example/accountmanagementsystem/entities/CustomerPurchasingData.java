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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_purchasing_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CustomerPurchasingData{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "purchasing_id")
  private Integer purchasingId;

  @Column(name = "customer_vat_tin")
  private Integer customerVatTin;

  @Column(name = "customer_gst_no")
  private String customerGstNbr;

  @Column(name = "customer_pan_no")
  private String customerPanNbr;

  @Column(name = "customer_tan_no")
  private String customerTanNbr;

  @Column(name = "customer_udyog_aadhar_no")
  private String customerUdyogAadharNbr;

  @ManyToOne
  @JoinColumn(name = "credit_term_code", nullable = false)
  private CreditTerm customerCreditTerm;

  @ManyToOne
  @JoinColumn(name = "dist_channel_code", nullable = false)
  private DistributionChannel distributionChannel;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private CustomerBasicDetail customerBasicDetail;
}