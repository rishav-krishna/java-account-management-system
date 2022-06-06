package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entities.EnterpriseDetail;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EntepriseRepository extends JpaRepository<EnterpriseDetail, Integer> {

  @Query(value = "select enterprise_code from enterprise_details WHERE organization_id = :organizationId order by enterprise_code desc limit 1", nativeQuery = true)
  Optional<Integer> findEnterpriseCodeByOrgId(Integer organizationId);
}
