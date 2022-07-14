package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entities.CustomerBasicDetail;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerBasicDetail, Integer> {

  @Query(value = "select customer_code from customer_basic_details WHERE organization_id = :organizationId order by customer_code desc limit 1", nativeQuery = true)
  Optional<String> findCustomerCodeByOrgId(Integer organizationId);

  @Query(value = "select * from customer_basic_details WHERE organization_id = :orgId", nativeQuery = true)
  List<CustomerBasicDetail> findAllByOrganizationDetail(Integer orgId);
}
