package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entities.CustomerBasicDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerBasicDetail, Integer> {
}
