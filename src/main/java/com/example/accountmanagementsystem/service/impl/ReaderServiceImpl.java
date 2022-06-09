package com.example.accountmanagementsystem.service.impl;

import com.example.accountmanagementsystem.Dtos.EnterpriseDto;
import com.example.accountmanagementsystem.Dtos.LocationDto;
import com.example.accountmanagementsystem.Dtos.OrganizationDto;
import com.example.accountmanagementsystem.entities.EnterpriseDetail;
import com.example.accountmanagementsystem.entities.LocationDetail;
import com.example.accountmanagementsystem.entities.OrganizationDetail;
import com.example.accountmanagementsystem.repository.EntepriseRepository;
import com.example.accountmanagementsystem.repository.LocationRepository;
import com.example.accountmanagementsystem.repository.OrganizationRepository;
import com.example.accountmanagementsystem.service.ReaderService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ReaderServiceImpl implements ReaderService {

  private final OrganizationRepository organizationRepository;
  private final EntepriseRepository entepriseRepository;
  private final LocationRepository locationRepository;

  public ReaderServiceImpl(
      final OrganizationRepository organizationRepository,
      final EntepriseRepository entepriseRepository,
      final LocationRepository locationRepository) {
    this.organizationRepository = organizationRepository;
    this.entepriseRepository = entepriseRepository;
    this.locationRepository = locationRepository;
  }

  @Override
  public OrganizationDto getOrganization(final Integer organizationId) {
    Optional<OrganizationDetail> organizationDetail = organizationRepository.findById(organizationId);
    if(organizationDetail.isPresent())
      return OrganizationDto.getOrganizationDto(organizationDetail.get());
    throw new RuntimeException("Invalid OrganizationId");
  }

  @Override
  public EnterpriseDto getEnterpriseDto(final Integer enterpriseId) {
    Optional<EnterpriseDetail> enterpriseDetail = entepriseRepository.findById(enterpriseId);
    if(enterpriseDetail.isPresent())
      return EnterpriseDto.getEnterpriseDto(enterpriseDetail.get());
    throw new RuntimeException("Invalid EnterpriseId");
  }

  @Override
  public LocationDto getLocationDto(final Integer locationId) {
    Optional<LocationDetail> locationDetail = locationRepository.findById(locationId);
    if(locationDetail.isPresent())
      return LocationDto.getLocationDto(locationDetail.get());
    throw new RuntimeException("Invalid EnterpriseId");
  }
}
