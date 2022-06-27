package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entities.CreditTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditTermRepository extends JpaRepository<CreditTerm, Integer> {
}
