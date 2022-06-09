package com.example.accountmanagementsystem.service.impl;

import static com.example.accountmanagementsystem.constants.ApplicationConstants.ENTERPRISE_ADD_VALUE;
import static com.example.accountmanagementsystem.constants.ApplicationConstants.ENTERPRISE_START_VALUE;
import static com.example.accountmanagementsystem.constants.ApplicationConstants.LOCATION_INCREMENT_VALUE;

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
  public OrganizationDetail addOrganization(final OrganizationRequest organization) {
    return organizationRepository.save(
        OrganizationDetail.builder()
            .orgName(organization.getOrganizationName())
            .address(organization.getOrganizationAddress())
            .enterpriseDetails(Collections.emptySet())
            .isActive(1)
            .build()
    );
  }

  @Override
  @Transactional
  public EnterpriseDetail addEnterpriseToOrganization(final EnterpriseRequest enterprise) {
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
      return entepriseRepository.save(EnterpriseDetail.builder()
          .enterpriseName(enterprise.getEnterpriseName())
          .enterpriseAddress(enterprise.getEnterpriseAddress())
          .enterpriseCode(enterpriseCode)
          .organizationDetail(organizationDetail.get())
          .build()
      );
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  @Transactional
  public EnterpriseDetail addEnterpriseAndLocation(final EnterpriseLocationRequest enterpriseLocationRequest) {
    EnterpriseRequest enterpriseRequest = EnterpriseRequest.builder()
        .enterpriseName(enterpriseLocationRequest.getEnterpriseName())
        .enterpriseAddress(enterpriseLocationRequest.getEnterpriseAddress())
        .organizationId(enterpriseLocationRequest.getOrganizationId())
        .build();
    EnterpriseDetail enterpriseDetail = addEnterpriseToOrganization(enterpriseRequest);
    LocationRequest locationRequest = LocationRequest.builder()
        .locationName(enterpriseLocationRequest.getLocationName())
        .locationAddress(enterpriseLocationRequest.getLocationAddress())
        .enterpriseId(enterpriseDetail.getEnterpriseId())
        .build();
    enterpriseDetail.setLocationDetailSet(Set.of(addLocationToEnterprise(locationRequest)));
    return enterpriseDetail;
  }

  @Override
  @Transactional
  public LocationDetail addLocationToEnterprise(LocationRequest locationRequest) {
    Optional<Integer> optionalLocationCode =
        locationRepository.findLocationCodeByEntId(locationRequest.getEnterpriseId());
    Optional<EnterpriseDetail> enterpriseDetail =
        entepriseRepository.findById(locationRequest.getEnterpriseId());
    if(enterpriseDetail.isEmpty()) {
      throw new IllegalStateException("Enterprise is not present for location");
    }

    Integer locationCode = optionalLocationCode.map(integer -> integer + LOCATION_INCREMENT_VALUE)
        .orElseGet(() -> enterpriseDetail.get().getEnterpriseCode() + LOCATION_INCREMENT_VALUE);

    Integer enterpriseCode = enterpriseDetail.get().getEnterpriseCode();
    return locationRepository.save(LocationDetail.builder()
        .locationName(locationRequest.getLocationName())
        .locationAddress(locationRequest.getLocationAddress())
        .locationCode(locationCode)
        .enterpriseDetail(enterpriseDetail.get())
        .accountEnt("a"+enterpriseCode)
        .taxEnt("t"+ enterpriseCode)
        .build());
  }

  @Override
  public List<ExpenseCenterDetail> addExpenseCenterToLocation(
      List<String> expenseCenterRequests, Integer locationId) {

    Optional<LocationDetail> optionalLocationDetail = locationRepository.findById(locationId);
    if(optionalLocationDetail.isEmpty()) {
      throw new IllegalStateException("Location is not present");
    }
    Optional<Integer> locationCode = expenseCenterRepository.findExpenseCodeByLocId(locationId);
    AtomicInteger count = new AtomicInteger(1);

    return expenseCenterRequests.stream().map(e -> {
      Integer centerCode = locationCode.orElseGet(() -> optionalLocationDetail.get().getLocationCode()) + count.getAndIncrement();
      return expenseCenterRepository.save(ExpenseCenterDetail.builder()
          .name(e)
          .centerCode(centerCode)
          .locationDetail(optionalLocationDetail.get())
          .build());
    }).collect(Collectors.toList());
  }
}
