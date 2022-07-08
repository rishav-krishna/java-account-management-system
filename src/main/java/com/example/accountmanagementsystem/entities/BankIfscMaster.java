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
@Table(name = "bank_ifsc_master")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class BankIfscMaster {

  @Id
  @Column(name = "branch_ifsc_code")
  private String branchIfscCode;

  @Column(name = "branch_name")
  private String branchName;

  @Column(name = "bank_name")
  private String bankName;

  @Column(name = "branch_address")
  private String branchAddress;
}
