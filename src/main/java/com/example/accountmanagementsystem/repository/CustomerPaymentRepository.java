package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entities.CustomerPaymentData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPaymentRepository extends JpaRepository<CustomerPaymentData, Integer> {

  @Query(value = "select cpd.customer_payment_id, cpd.customer_account_number, cpd.branch_ifsc_code, cpd.account_type, " +
      "cpd.account_holder_name from accountERP.customer_payment_data cpd " +
      "inner join accountERP.customer_bank_intermidate cbi on cpd.customer_payment_id = cbi.customer_payment_id\n" +
      "inner join accountERP.customer_basic_details cbd on cbd.customer_id = cbi.customer_id\n" +
      "where cbd.customer_id=:customerId", nativeQuery = true)
  List<CustomerPaymentData> findAllByCustomerBasicDetail(@Param("customerId") Integer customerId);
}
