package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entities.OrganizationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationDetail, Integer> {
}
