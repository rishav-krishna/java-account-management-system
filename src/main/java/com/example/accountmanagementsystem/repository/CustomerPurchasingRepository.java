package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entities.CustomerPurchasingData;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPurchasingRepository extends JpaRepository<CustomerPurchasingData, Integer> {
  @Query(value = "select * from customer_purchasing_data where customer_id = :customerId", nativeQuery = true)
  List<CustomerPurchasingData> findByCustomerBasicDetail(@Param("customerId") Integer customerId);
}
