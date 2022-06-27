package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entities.ExpenseCenterDetail;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseCenterRepository extends JpaRepository<ExpenseCenterDetail, Integer> {

  @Query(value = "select expense_center_code from expense_center_details WHERE location_id = :locationId order by expense_center_code desc limit 1", nativeQuery = true)
  Optional<Integer> findExpenseCodeByLocId(Integer locationId);

  @Query(value = "select * from expense_center_details ecd where ecd.location_id =:locationId", nativeQuery = true)
  List<ExpenseCenterDetail> findExpenseCenterDetailsByLocationDetail(Integer locationId);
}
