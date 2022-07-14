package com.example.accountmanagementsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credit_terms")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreditTerm {

  @Id
  @Column(name = "credit_term_code", nullable = false)
  private String creditTermCode;

  @Column(name = "credit_term_name")
  private String creditTermName;
}
