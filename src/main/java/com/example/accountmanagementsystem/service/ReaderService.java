package com.example.accountmanagementsystem.service;

import com.example.accountmanagementsystem.Dtos.EnterpriseDto;
import com.example.accountmanagementsystem.Dtos.LocationDto;
import com.example.accountmanagementsystem.Dtos.OrganizationDto;

public interface ReaderService {

  OrganizationDto getOrganization(Integer organizationId);
  EnterpriseDto getEnterpriseDto(Integer enterpriseId);
  LocationDto getLocationDto(Integer locationId);
}
