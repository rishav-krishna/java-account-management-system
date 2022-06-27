package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entities.CustomerPurchasingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPurchasingRepository extends JpaRepository<CustomerPurchasingData, Integer> {
}
