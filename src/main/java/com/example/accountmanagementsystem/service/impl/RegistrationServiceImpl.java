package com.example.accountmanagementsystem.service.impl;

import static com.example.accountmanagementsystem.constants.ApplicationConstants.ENTERPRISE_ADD_VALUE;
import static com.example.accountmanagementsystem.constants.ApplicationConstants.ENTERPRISE_START_VALUE;
import static com.example.accountmanagementsystem.constants.ApplicationConstants.LOCATION_INCREMENT_VALUE;

import com.example.accountmanagementsystem.Dtos.EnterpriseDto;
import com.example.accountmanagementsystem.Dtos.ExpenseCenterDto;
import com.example.accountmanagementsystem.Dtos.LocationDto;
import com.example.accountmanagementsystem.Dtos.OrganizationDto;
import com.example.accountmanagementsystem.entities.EnterpriseDetail;
import com.example.accountmanagementsystem.entities.ExpenseCenterDetail;
import com.example.accountmanagementsystem.entities.LocationDetail;
import com.example.accountmanagementsystem.entities.OrganizationDetail;
import com.example.accountmanagementsystem.model.EnterpriseLocationRequest;
import com.example.accountmanagementsystem.model.EnterpriseRequest;
import com.example.accountmanagementsystem.model.LocationRequest;
import com.example.accountmanagementsystem.model.OrganizationRequest;
import com.example.accountmanagementsystem.repository.EntepriseRepository;
import com.example.accountmanagementsystem.repository.ExpenseCenterRepository;
import com.example.accountmanagementsystem.repository.LocationRepository;
import com.example.accountmanagementsystem.repository.OrganizationRepository;
import com.example.accountmanagementsystem.service.RegistrationService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

  private final OrganizationRepository organizationRepository;
  private final EntepriseRepository entepriseRepository;
  private final LocationRepository locationRepository;
  private final ExpenseCenterRepository expenseCenterRepository;

  public RegistrationServiceImpl(
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
  public OrganizationDto addOrganization(final OrganizationRequest organization) {
    OrganizationDetail organizationDetail = organizationRepository.save(
        OrganizationDetail.builder()
            .orgName(organization.getOrganizationName())
            .address(organization.getOrganizationAddress())
            .isActive(1)
            .build()
    );
    return OrganizationDto.getOrganizationDto(organizationDetail, Collections.emptyList());
  }

  @Override
  @Transactional
  public EnterpriseDto addEnterpriseToOrganization(final EnterpriseRequest enterprise) {
    Optional<OrganizationDetail> organizationDetail =
        organizationRepository.findById(enterprise.getOrganizationId());
    int enterpriseCode = ENTERPRISE_ADD_VALUE;
    try {
      Optional<Integer> enterpriseCodeByOrgId =
          entepriseRepository.findEnterpriseCodeByOrgId(enterprise.getOrganizationId());
      if(enterpriseCodeByOrgId.isEmpty()) {
        enterpriseCode += enterprise.getOrganizationId() * ENTERPRISE_START_VALUE;
      } else {
        enterpriseCode += enterpriseCodeByOrgId.get();
      }
      EnterpriseDetail enterpriseDetail = entepriseRepository.save(EnterpriseDetail.builder()
          .enterpriseName(enterprise.getEnterpriseName())
          .enterpriseAddress(enterprise.getEnterpriseAddress())
          .enterpriseCode(enterpriseCode)
          .organizationDetail(organizationDetail.get())
          .build()
      );
      return EnterpriseDto.getEnterpriseDto(enterpriseDetail, Collections.emptyList());
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  @Transactional
  public EnterpriseDto addEnterpriseAndLocation(final EnterpriseLocationRequest enterpriseLocationRequest) {
    EnterpriseRequest enterpriseRequest = EnterpriseRequest.builder()
        .enterpriseName(enterpriseLocationRequest.getEnterpriseName())
        .enterpriseAddress(enterpriseLocationRequest.getEnterpriseAddress())
        .organizationId(enterpriseLocationRequest.getOrganizationId())
        .build();
    EnterpriseDto enterpriseDto = addEnterpriseToOrganization(enterpriseRequest);
    LocationRequest.LocationData locationData = new LocationRequest.LocationData(
        enterpriseLocationRequest.getLocationName(),
        enterpriseLocationRequest.getLocationAddress());
    List<LocationDetail> locationDetails =
        addLocationToEnterprise(new LocationRequest(List.of(locationData)), enterpriseDto.getEnterpriseId());
    enterpriseDto.setLocations(locationDetails);
    return enterpriseDto;
  }

  @Override
  @Transactional
  public List<LocationDetail> addLocationToEnterprise(LocationRequest locationRequest, Integer enterpriseId) {
    Optional<Integer> optionalLocationCode =
        locationRepository.findLocationCodeByEntId(enterpriseId);
    Optional<EnterpriseDetail> enterpriseDetail =
        entepriseRepository.findById(enterpriseId);
    if(enterpriseDetail.isEmpty()) {
      throw new IllegalStateException("Enterprise is not present for location");
    }

    Integer enterpriseCode = enterpriseDetail.get().getEnterpriseCode();
    AtomicInteger locationCode = new AtomicInteger(optionalLocationCode.map(integer -> integer + LOCATION_INCREMENT_VALUE)
        .orElseGet(() -> enterpriseDetail.get().getEnterpriseCode() + LOCATION_INCREMENT_VALUE));
    return locationRequest.getLocationData().stream().map(l->{
      Integer lc = locationCode.get();
      locationCode.getAndAdd(LOCATION_INCREMENT_VALUE);
      return locationRepository.save(LocationDetail.builder()
          .locationName(l.getLocationName())
          .locationAddress(l.getLocationAddress())
          .locationCode(lc)
          .enterpriseDetail(enterpriseDetail.get())
          .accountEnt("a" + enterpriseCode)
          .taxEnt("t" + enterpriseCode)
          .build());
    }).collect(Collectors.toList());
  }

  @Override
  public List<ExpenseCenterDto> addExpenseCenterToLocation(
      List<String> expenseCenterRequests, Integer locationId) {

    Optional<LocationDetail> optionalLocationDetail = locationRepository.findById(locationId);
    if(optionalLocationDetail.isEmpty()) {
      throw new IllegalStateException("Location is not present");
    }
    Optional<Integer> locationCode = expenseCenterRepository.findExpenseCodeByLocId(locationId);
    AtomicInteger count = new AtomicInteger(1);

    List<ExpenseCenterDetail> expenseCenterDetails = expenseCenterRequests.stream().map(e -> {
      Integer centerCode =
          locationCode.orElseGet(() -> optionalLocationDetail.get().getLocationCode()) +
              count.getAndIncrement();
      return expenseCenterRepository.save(ExpenseCenterDetail.builder()
          .name(e)
          .centerCode(centerCode)
          .locationDetail(optionalLocationDetail.get())
          .build());
    }).collect(Collectors.toList());
    return ExpenseCenterDto.getExpenseCenterDtos(expenseCenterDetails);
  }
}
