package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entities.CityMaster;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityMasterRepository extends JpaRepository<CityMaster, Integer> {

  Optional<CityMaster> findCityMasterByCityPinCode(Integer pinCode);
}