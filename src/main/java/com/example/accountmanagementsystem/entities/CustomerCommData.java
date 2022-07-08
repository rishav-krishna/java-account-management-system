package com.example.accountmanagementsystem.entities;

import java.util.HashSet;
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
  private Set<CustomerBasicDetail> customerBasicDetails = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CustomerCommData that = (CustomerCommData) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (communicationData != null ? !communicationData.equals(that.communicationData) :
        that.communicationData != null) {
      return false;
    }
    if (communicationType != null ? !communicationType.equals(that.communicationType) :
        that.communicationType != null) {
      return false;
    }
    return customerBasicDetails != null ? customerBasicDetails.equals(that.customerBasicDetails) :
        that.customerBasicDetails == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (communicationData != null ? communicationData.hashCode() : 0);
    result = 31 * result + (communicationType != null ? communicationType.hashCode() : 0);
    result = 31 * result + (customerBasicDetails != null ? customerBasicDetails.hashCode() : 0);
    return result;
  }
}
