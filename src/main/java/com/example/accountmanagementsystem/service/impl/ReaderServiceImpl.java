package com.example.accountmanagementsystem.service.impl;

import com.example.accountmanagementsystem.Dtos.EnterpriseDto;
import com.example.accountmanagementsystem.Dtos.LocationDto;
import com.example.accountmanagementsystem.Dtos.OrganizationDto;
import com.example.accountmanagementsystem.entities.EnterpriseDetail;
import com.example.accountmanagementsystem.entities.ExpenseCenterDetail;
import com.example.accountmanagementsystem.entities.LocationDetail;
import com.example.accountmanagementsystem.entities.OrganizationDetail;
import com.example.accountmanagementsystem.repository.EntepriseRepository;
import com.example.accountmanagementsystem.repository.ExpenseCenterRepository;
import com.example.accountmanagementsystem.repository.LocationRepository;
import com.example.accountmanagementsystem.repository.OrganizationRepository;
import com.example.accountmanagementsystem.service.ReaderService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ReaderServiceImpl implements ReaderService {

  private final OrganizationRepository organizationRepository;
  private final EntepriseRepository entepriseRepository;
  private final LocationRepository locationRepository;
  private final ExpenseCenterRepository expenseCenterRepository;

  public ReaderServiceImpl(
      final OrganizationRepository organizationRepository,
      final EntepriseRepository entepriseRepository,
      final LocationRepository locationRepository,
      final ExpenseCenterRepository expenseCenterRepository) {
    this.organizationRepository = organizationRepository;
    this.entepriseRepository = entepriseRepository;
    this.locationRepository = locationRepository;
    this.expenseCenterRepository = expenseCenterRepository;
  }

  @Override
  public OrganizationDto getOrganization(final Integer organizationId) {
    Optional<OrganizationDetail> organizationDetail = organizationRepository.findById(organizationId);
    List<EnterpriseDetail> enterpriseDetails =
        entepriseRepository.findEnterpriseDetailsByOrganizationDetail(organizationId);
    if(organizationDetail.isPresent())
      return OrganizationDto.getOrganizationDto(organizationDetail.get(), enterpriseDetails);
    throw new RuntimeException("Invalid OrganizationId");
  }

  @Override
  public EnterpriseDto getEnterpriseDto(final Integer enterpriseId) {
    Optional<EnterpriseDetail> enterpriseDetail = entepriseRepository.findById(enterpriseId);
    List<LocationDetail> locationDetails =
        locationRepository.findLocationDetailsByEnterpriseDetail(enterpriseId);
    if(enterpriseDetail.isPresent())
      return EnterpriseDto.getEnterpriseDto(enterpriseDetail.get(), locationDetails);
    throw new RuntimeException("Invalid EnterpriseId");
  }

  @Override
  public LocationDto getLocationDto(final Integer locationId) {
    Optional<LocationDetail> locationDetail = locationRepository.findById(locationId);
    List<ExpenseCenterDetail> expenseCenterDetails =
        expenseCenterRepository.findExpenseCenterDetailsByLocationDetail(locationId);
    if(locationDetail.isPresent())
      return LocationDto.getLocationDto(locationDetail.get(), expenseCenterDetails);
    throw new RuntimeException("Invalid LocationId");
  }

  @Override
  public List<LocationDto> getLocationDtos(final List<LocationDetail> locationDetails) {
    return locationDetails.stream().map(l -> {
      List<ExpenseCenterDetail> expenseCenterDetails =
          expenseCenterRepository.findExpenseCenterDetailsByLocationDetail(l.getLocationId());
      return LocationDto.getLocationDto(l, expenseCenterDetails);
    }).collect(Collectors.toList());
  }
}
