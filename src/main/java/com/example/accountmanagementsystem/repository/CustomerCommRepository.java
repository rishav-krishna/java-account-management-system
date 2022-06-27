package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entities.CustomerCommData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCommRepository extends JpaRepository<CustomerCommData, Integer> {
}
