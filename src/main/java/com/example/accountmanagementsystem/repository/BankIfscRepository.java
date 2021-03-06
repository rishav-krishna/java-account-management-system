package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entities.BankIfscMaster;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankIfscRepository extends JpaRepository<BankIfscMaster, Integer> {
  Optional<BankIfscMaster> findBankIfscMasterByBranchIfscCode(String branchIfscCode);
}
