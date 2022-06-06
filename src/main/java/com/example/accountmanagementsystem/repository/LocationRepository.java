package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entities.LocationDetail;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationDetail, Integer> {
  @Query(value = "select location_code from location_details WHERE enterprise_id = :enterpriseId order by location_code desc limit 1", nativeQuery = true)
  Optional<Integer> findLocationCodeByEntId(Integer enterpriseId);
}
