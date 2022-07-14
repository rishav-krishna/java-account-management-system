package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entities.CustomerCommData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCommRepository extends JpaRepository<CustomerCommData, Integer> {

  @Query(value = "select ccd.id, ccd.communication_type, ccd.communication_data from accountERP.customer_comm_data ccd " +
      "inner join accountERP.customer_comm_intermidate cci on ccd.id = cci.customer_comm_id\n" +
      "inner join accountERP.customer_basic_details cbd on cbd.customer_id = cci.customer_id\n" +
      "where cbd.customer_id= :customerId", nativeQuery = true)
  List<CustomerCommData> findAllByCustomerBasicDetail(@Param("customerId") Integer customerId);
}
